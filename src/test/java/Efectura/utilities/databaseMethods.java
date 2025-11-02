package Efectura.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class databaseMethods {


    public static Map<String, Integer> getUrunVeStockOutSayilariW43() {
        final String query = "WITH filtered AS (\n" +
                "  SELECT\n" +
                "    DISTRIBUTOR_KOD,\n" +
                "    URUN_TIP_ACIKLAMA,\n" +
                "    toFloat64(ifNull(Stock_Liters, 0))                  AS stock_l,\n" +
                "    greatest(0., toFloat64(ifNull(Est_Total_Sales_Liters, 0))) AS est_l\n" +
                "  FROM my_database.EstSalesAndCurrStocks\n" +
                "  WHERE DISTRIBUTOR_KOD IN (\n" +
                "    'ASYA KAYIKCI','ADIYAMAN DATA','AGRI TANRIVERDI','ANKARA GRAM','ANKARA LACIN',\n" +
                "    'ANTALYA ANDA','ANTALYA ANKA','ANTALYA INCI','ARTVIN KESKIN','ASYA DOGUS',\n" +
                "    'AYDIN PIRIM','BAYIR ACARLAR','BODRUM PIRIM','BURSA RIT','CAGAN',\n" +
                "    'CANAKKALE BAYRAKTAR','DENIZLI BIZIMYAKI','DIYARBAKIR HNR','DUNYA ZOGULDAK',\n" +
                "    'ELAZIG POLAT','GAZIANTEP EKER','ISPARTA CANTAYLAR','ISTANBUL DOGUS','ISTANBUL GURPA',\n" +
                "    'ISTANBUL KAYIKCI','ISTANBUL OZYIGIT','ISTANBUL PIRIM','IZMIR GURPA','IZMIR PIRIM',\n" +
                "    'KARAMAN ALFA','KASTAMONU LACIN','KAYSERI 4GEN','KIRSEHIR YUKSELLER','KOCAELI GULENER',\n" +
                "    'MALATYA OZSAH','MANISA CANTAY','MANISA CANTAY 2','MARDIN SECEM','MERSIN ALFA',\n" +
                "    'MERSIN SGM','MUGLA ACARLAR','NEVSEHIR ONUR','ORDU GURESCIOGLU','OSMANIYE MARSAS',\n" +
                "    'SAKARYA KOSEOGLU','SAMSUN TANRIVERDI','SIVAS SES','TRAKYA ERZA','URFA UCKOK',\n" +
                "    'USAK BIZIMYAKI','YALOVA KUZEYNAM','CORUM TANRIVERDI'\n" +
                "  )\n" +
                "),\n" +
                "cat AS (\n" +
                "  SELECT\n" +
                "    DISTRIBUTOR_KOD,\n" +
                "    URUN_TIP_ACIKLAMA,\n" +
                "    sum(stock_l) AS stock_l_sum,\n" +
                "    sum(est_l)   AS est_l_sum\n" +
                "  FROM filtered\n" +
                "  GROUP BY DISTRIBUTOR_KOD, URUN_TIP_ACIKLAMA\n" +
                ")\n" +
                "SELECT\n" +
                "  URUN_TIP_ACIKLAMA,\n" +
                "  countDistinctIf(DISTRIBUTOR_KOD, stock_l_sum / nullIf(est_l_sum, 0) < 10)\n" +
                "    AS num_distributors_with_so\n" +
                "FROM cat\n" +
                "GROUP BY URUN_TIP_ACIKLAMA\n" +
                "ORDER BY num_distributors_with_so DESC\n" +
                "LIMIT 10;";

        Map<String, Integer> urunVeStockOutSayilariW43 = new HashMap<>();

        try (Connection conn = DatabaseManager.getConnection(
                DbConfigs.DIA_CLICKHOUSE, DbConfigs.DIA_CLICKHOUSE_USERNAME, DbConfigs.DIA_CLICKHOUSE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Kolon adlarıyla güvenli okuma
                final String urun = rs.getString("URUN_TIP_ACIKLAMA");
                final int value = rs.getInt("num_distributors_with_so");

//                System.out.println(urun + ": " + value);

                urunVeStockOutSayilariW43.put(urun,value);
            }

            // İsteğe bağlı: log
            System.out.println("W43 ürün bazlı stock-out haritası: " + urunVeStockOutSayilariW43);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return urunVeStockOutSayilariW43;
    }

    public static int getStockOutSumW43() {
        final String query = " SELECT\n" +
                " `Distribütör`,\n" +
                "  count() AS c\n" +
                "FROM\n" +
                "(\n" +
                "  SELECT\n" +
                "    DISTRIBUTOR_KOD        AS `Distribütör`,\n" +
                "    URUN_KATEGORU_ACIKLAMA AS `Ürün Kategori`,\n" +
                "    sum(toFloat64(ifNull(Stock_Liters, 0)))                          AS `stok_sum`,\n" +
                "    sum(greatest(0., toFloat64(ifNull(Est_Total_Sales_Liters, 0))))  AS `est_sum`\n" +
                "  FROM my_database.EstSalesAndCurrStocks\n" +
                "  WHERE DISTRIBUTOR_KOD IN ('ASYA KAYIKCI','ADIYAMAN DATA','AGRI TANRIVERDI','ANKARA GRAM','ANKARA LACIN',\n" +
                "    'ANTALYA ANDA','ANTALYA ANKA','ANTALYA INCI','ARTVIN KESKIN','ASYA DOGUS',\n" +
                "    'AYDIN PIRIM','BAYIR ACARLAR','BODRUM PIRIM','BURSA RIT','CAGAN',\n" +
                "    'CANAKKALE BAYRAKTAR','DENIZLI BIZIMYAKI','DIYARBAKIR HNR','DUNYA ZOGULDAK',\n" +
                "    'ELAZIG POLAT','GAZIANTEP EKER','ISPARTA CANTAYLAR','ISTANBUL DOGUS','ISTANBUL GURPA',\n" +
                "    'ISTANBUL KAYIKCI','ISTANBUL OZYIGIT','ISTANBUL PIRIM','IZMIR GURPA','IZMIR PIRIM',\n" +
                "    'KARAMAN ALFA','KASTAMONU LACIN','KAYSERI 4GEN','KIRSEHIR YUKSELLER','KOCAELI GULENER',\n" +
                "    'MALATYA OZSAH','MANISA CANTAY','MANISA CANTAY 2','MARDIN SECEM','MERSIN ALFA',\n" +
                "    'MERSIN SGM','MUGLA ACARLAR','NEVSEHIR ONUR','ORDU GURESCIOGLU','OSMANIYE MARSAS',\n" +
                "    'SAKARYA KOSEOGLU','SAMSUN TANRIVERDI','SIVAS SES','TRAKYA ERZA','URFA UCKOK',\n" +
                "    'USAK BIZIMYAKI','YALOVA KUZEYNAM','CORUM TANRIVERDI')\n" +
                "    AND DISTRIBUTOR_KOD NOT IN ('EDIRNE MAHALO')\n" +
                "  GROUP BY `Distribütör`, `Ürün Kategori`\n" +
                ")\n" +
                "WHERE `stok_sum` / nullIf(`est_sum`, 0) < 10\n" +
                "GROUP BY `Distribütör`\n" +
                "ORDER BY c desc LIMIT 10;";

        int stockOutCount = 0;

        try (Connection conn = DatabaseManager.getConnection(
                DbConfigs.DIA_CLICKHOUSE, DbConfigs.DIA_CLICKHOUSE_USERNAME, DbConfigs.DIA_CLICKHOUSE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Kolon adlarıyla güvenli okuma
                stockOutCount += rs.getInt("c");
            }

            // İsteğe bağlı: log
            System.out.println("W43 stockOutCount: " + stockOutCount);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockOutCount;

    }

    public static String getBmNameS26() {
        String query = "select DISTINCT\n" +
                "        BM_Name\n" +
                "from\n" +
                "        my_database.HierarchyInfo hi\n" +
                "WHERE\n" +
                "        ((splitByChar(' ', upperUTF8(replaceAll(replaceAll(ifNull(BM_Code, ''), 'ı', 'i'), 'İ', 'I')))[1] LIKE splitByChar(' ', upperUTF8(replaceAll(replaceAll('Marmara', 'ı', 'i'), 'İ', 'I')))[1]))";

        String bmName = null;

        try (Connection conn = DatabaseManager.getConnection(
                DbConfigs.DIA_CLICKHOUSE, DbConfigs.DIA_CLICKHOUSE_USERNAME, DbConfigs.DIA_CLICKHOUSE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Kolon adlarıyla güvenli okuma
                bmName = rs.getString("BM_Name");
            }

            // İsteğe bağlı: log
            System.out.println("bmNameS26: " + bmName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bmName;

    }

    public static double getTotalSales30() {
        String query = "select\n" +
                "toFloat64(SUM(IF(sf.BYTTUR IN (0,3),  sf.URUN_AMBALAJ_LITRE * sf.INVOICE_QUANTITY,\n" +
                "                         IF(sf.BYTTUR IN (2,4), -sf.URUN_AMBALAJ_LITRE * sf.INVOICE_QUANTITY, 0)))) AS Total_Sales\n" +
                "    FROM my_database.staging_fatura_v2 sf\n" +
                "    WHERE sf.BYTDURUM = 0\n" +
                "      AND sf.LNGDISTKOD NOT IN (1,2,384,999)\n" +
                "      AND sf.MUSTERI_KOD != 'Mey İçki'\n" +
                "      AND TRHISLEMTARIHI >= today()\n" +
                "      AND EK_BOLGEMUDURLUGU = 'Marmara Bölge'\n" +
                "      AND EK_ROUTE_NAME NOT ILIKE '%LZM%'";

        double totalSales = 0;

        try (Connection conn = DatabaseManager.getConnection(
                DbConfigs.DIA_CLICKHOUSE, DbConfigs.DIA_CLICKHOUSE_USERNAME, DbConfigs.DIA_CLICKHOUSE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Kolon adlarıyla güvenli okuma
                totalSales = rs.getDouble("Total_Sales");
            }

            // İsteğe bağlı: log
            System.out.println("-----\ntotalSalesS30: " + totalSales + "\n-----");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalSales;


    }

}
