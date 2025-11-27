package Efectura.stepDefs;

import Efectura.utilities.Requests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.util.*;

public class WidgetsStepDefs extends BaseStep {

    double pitchedCountW36;
    double pilotCountW36;

    @Given("The user send widget36 request")
    public void theUserSendWidget36Request() throws IOException {
        JSONObject w36Json = Requests.sendWidget36Request();
        System.out.println("w36Json: " + w36Json);

        JSONArray dataArray = w36Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        pitchedCountW36 = 0;
        pilotCountW36 = 0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);

            // Alan adlarını esnek oku
            String stage = obj.optString("Sales Stage",
                    obj.optString("Sales Stage_5c2664", null));

            double count = obj.has("Customer Count")
                    ? obj.optDouble("Customer Count", 0.0)
                    : obj.optDouble("Customer Count_4aa1e1", 0.0);

            if ("3-Pitched".equals(stage)) {
                pitchedCountW36 = count;
            } else if ("8-Pilot".equals(stage)) {
                pilotCountW36 = count;
            }
        }

        System.out.println("3-Pitched Customer Count W36: " + pitchedCountW36);
        System.out.println("8-Pilot Customer Count W36: " + pilotCountW36);
    }


    double totalAssetCountW37;
    @Given("The user send widget37 request")
    public void theUserSendWidget37Request() throws IOException {
        JSONObject w37Json = Requests.sendWidget37Request();
        System.out.println("w37Json: " + w37Json);

        JSONArray dataArray = w37Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalAssetCountW37 = 0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);

            // Değerleri oku
            double count = obj.optDouble("COUNT_DISTINCT(AssetId)",
                    obj.optDouble("COUNT_DISTINCT(AssetId)_759be0", 0.0));

            totalAssetCountW37 += count;
        }

        System.out.println("Toplam COUNT_DISTINCT(AssetId) W37: " + totalAssetCountW37);
    }

    @Then("The user verify scenario1")
    public void theUserVerifyScenario1() {
        Assert.assertEquals("Senaryo 1 sayılar eşleşmedi", pitchedCountW36, totalAssetCountW37, 0.01);
    }

    double totalPilotCountW38;
    double totalLiveCountW38;

    @Given("The user send widget38 request")
    public void theUserSendWidget38Request() throws IOException {
        JSONObject w38Json = Requests.sendWidget38Request();
        System.out.println("w38Json: " + w38Json);

        JSONArray dataArray = w38Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotCountW38 = 0.0;
        totalLiveCountW38 = 0.0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);

            // 8-Pilot kolonunu oku
            double pilot = obj.has("8-Pilot")
                    ? obj.optDouble("8-Pilot", 0.0)
                    : obj.optDouble("8-Pilot_5c2664", 0.0);

            // 9-Live kolonunu oku
            double live = obj.has("9-Live")
                    ? obj.optDouble("9-Live", 0.0)
                    : obj.optDouble("9-Live_5c2664", 0.0);

            totalPilotCountW38 += pilot;
            totalLiveCountW38 += live;
        }

        System.out.println("Toplam 8-Pilot Customer Count W38: " + totalPilotCountW38);
        System.out.println("Toplam 9-Live Customer Count W38: " + totalLiveCountW38);
    }

    @Then("The user verify scenario2")
    public void theUserVerifyScenario2() {
        Assert.assertEquals("Senaryo 2 sayılar eşleşmedi",
                pilotCountW36, totalPilotCountW38, 0.01);
    }

    double totalPilotCountW39;
    double totalLiveCountW39;

    @Given("The user send widget39 request")
    public void theUserSendWidget39Request() throws IOException {
        JSONObject w39Json = Requests.sendWidget39Request();
        System.out.println("w39Json: " + w39Json);

        JSONArray dataArray = w39Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotCountW39 = 0.0;
        totalLiveCountW39 = 0.0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);

            // Stage ismini esnek oku (suffix'e karşı fallback)
            String stage = obj.optString("Sales Stage",
                    obj.optString("Sales Stage_5c2664", null));

            // Customer Count'u oku
            double count = obj.has("Customer Count")
                    ? obj.optDouble("Customer Count", 0.0)
                    : obj.optDouble("Customer Count_4aa1e1", 0.0);

            // Stage'e göre toplama
            if ("Pilot".equals(stage)) {
                totalPilotCountW39 += count;
            } else if ("Live".equals(stage)) {
                totalLiveCountW39 += count;
            }
        }

        System.out.println("Toplam Pilot (Customer Count) W39: " + totalPilotCountW39);
        System.out.println("Toplam Live (Customer Count) W39: " + totalLiveCountW39);
    }

    @Then("The user verify scenario3")
    public void theUserVerifyScenario3() {
        Assert.assertEquals("Senaryo 3 sayılar eşleşmedi",
                pilotCountW36, totalPilotCountW39, 0.01);
    }

    @Then("The user verify scenario4")
    public void theUserVerifyScenario4() {
        Assert.assertEquals("Senaryo 4 sayılar eşleşmedi",
                totalPilotCountW38, totalPilotCountW39, 0.01);

        Assert.assertEquals("Senaryo 4 sayılar eşleşmedi",
                totalLiveCountW38, totalLiveCountW39, 0.01);
    }


    // W3: Tüm SalesRep'ler için verilen stage sütununu topla (mevcut fonksiyon korunuyor)
    public static double sumStageAcrossAllRepsFromW3(JSONObject resp, String stageColName) {
        if (resp == null || stageColName == null) return 0.0;
        double sum = 0.0;
        try {
            JSONArray data = resp.getJSONArray("result").getJSONObject(0).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject row = data.getJSONObject(i);
                if (row.has(stageColName) && !row.isNull(stageColName)) {
                    sum += row.optDouble(stageColName, 0.0);
                }
            }
        } catch (Exception ignored) {}
        return sum;
    }

    // (Opsiyonel) Kolon adını case-insensitive bulma (küçük sapmalar için)
    private static String resolveColumnName(JSONObject resp, String expected) {
        try {
            JSONArray colnames = resp.getJSONArray("result").getJSONObject(0).getJSONArray("colnames");
            // Önce tam eşleşme
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                if (c.equals(expected)) return c;
            }
            // Sonra case-insensitive yaklaşımı
            String expLower = expected.toLowerCase();
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                if (c.toLowerCase().equals(expLower)) return c;
            }
        } catch (Exception ignored) {}
        return expected; // Bulamazsa verilen ismi kullan
    }

    // W3: Tüm temel stage toplamlarını tek seferde getir
    public static Map<String, Double> getAllStageTotalsFromW3(JSONObject resp) {
        Map<String, Double> totals = new LinkedHashMap<>();

        // İstediğin başlıklar (sıra korunsun diye LinkedHashMap)
        String[] wanted = new String[] {
                "1-Prospect",
                "2-Lead",
                "3-Pitched",
                "4-Contract",
                "5-Onb. Risk",
                "6-Onb. Operation",
                "7-Implementation",
                "8-Pilot",
                "9-Live",
                "10-Reject"
        };

        for (String w : wanted) {
            String col = resolveColumnName(resp, w);
            totals.put(w, sumStageAcrossAllRepsFromW3(resp, col));
        }
        return totals;
    }

    // Mevcut fonksiyonun (Pilot & Live) korunuyor
    public static double[] getPilotAndLiveTotalsFromW3(JSONObject resp) {
        String pilotCol = resolveColumnName(resp, "8-Pilot");
        String liveCol  = resolveColumnName(resp, "9-Live");
        double pilotTotal = sumStageAcrossAllRepsFromW3(resp, pilotCol);
        double liveTotal  = sumStageAcrossAllRepsFromW3(resp, liveCol);
        return new double[]{pilotTotal, liveTotal};
    }


    // === Step alanları ===
    double totalProspectW3, totalLeadW3, totalPitchedW3, totalContractW3,
            totalOnbRiskW3, totalPilotW3, totalLiveW3, totalRejectW3,totalOnbOperationW3,totalImplW3;

    @Given("The user send widget3 request")
    public void theUserSendWidget3Request() throws Exception {
        JSONObject w3Json = Requests.sendWidget3Request();
        System.out.println("w3Json: " + w3Json);

        Map<String, Double> totals = getAllStageTotalsFromW3(w3Json);

        totalProspectW3 = totals.getOrDefault("1-Prospect", 0.0);
        totalLeadW3     = totals.getOrDefault("2-Lead", 0.0);
        totalPitchedW3  = totals.getOrDefault("3-Pitched", 0.0);
        totalContractW3 = totals.getOrDefault("4-Contract", 0.0);
        totalOnbRiskW3  = totals.getOrDefault("5-Onb. Risk", 0.0);
        totalOnbOperationW3  = totals.getOrDefault("6-Onb. Operation", 0.0);
        totalImplW3  = totals.getOrDefault("7-Implementation", 0.0);
        totalPilotW3    = totals.getOrDefault("8-Pilot", 0.0);
        totalLiveW3     = totals.getOrDefault("9-Live", 0.0);
        totalRejectW3   = totals.getOrDefault("10-Reject", 0.0);

        System.out.println("Toplam 1-Prospect      : " + totalProspectW3);
        System.out.println("Toplam 2-Lead          : " + totalLeadW3);
        System.out.println("Toplam 3-Pitched       : " + totalPitchedW3);
        System.out.println("Toplam 4-Contract      : " + totalContractW3);
        System.out.println("Toplam 5-Onb. Risk     : " + totalOnbRiskW3);
        System.out.println("Toplam 6-Onb. Operation: " + totalOnbOperationW3);
        System.out.println("Toplam 7-Implementation: " + totalImplW3);
        System.out.println("Toplam 8-Pilot         : " + totalPilotW3);
        System.out.println("Toplam 9-Live          : " + totalLiveW3);
        System.out.println("Toplam 10-Reject       : " + totalRejectW3);
    }


    double totalPilotW40, totalLiveW40, totalImplementationW40;

    @Given("The user send widget40 request")
    public void theUserSendWidget40Request() throws IOException {
        JSONObject w40Json = Requests.sendWidget40Request();
        System.out.println("w40Json: " + w40Json);

        JSONArray dataArray = w40Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotW40          = 0.0;
        totalLiveW40           = 0.0;
        totalImplementationW40 = 0.0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);

            totalPilotW40          += obj.optDouble("8-Pilot",         0.0);
            totalLiveW40           += obj.optDouble("9-Live",          0.0);
            totalImplementationW40 += obj.optDouble("7-Implementation",0.0);
        }

        System.out.println("Toplam 8-Pilot (W40): " + totalPilotW40);
        System.out.println("Toplam 9-Live  (W40): " + totalLiveW40);
        System.out.println("Toplam 7-Implementation (W40): " + totalImplementationW40);
    }

    @Then("The user verify scenario5")
    public void theUserVerifyScenario5() {
//        Assert.assertEquals("Senaryo 5 pilot sayılar eşleşmedi",
//                totalPilotW3, totalPilotW40, 0.01);
//
//        Assert.assertEquals("Senaryo 5 live sayılar eşleşmedi",
//                totalLiveW3, totalLiveW40, 0.01);

        Assert.assertEquals("Senaryo 5 implementation sayılar eşleşmedi",
                totalImplW3, totalImplementationW40, 0.01);
    }

    double totalPilotW4, totalLiveW4, totalImplementationW4;

    @Given("The user send widget4 request")
    public void theUserSendWidget4Request() throws IOException {
        // JSON'ı çek (senin mevcut request metodunla)
        JSONObject w4Json = Requests.sendWidget4Request();
        System.out.println("w4Json: " + w4Json);

        if (w4Json == null) {
            totalPilotW4 = 0;
            totalLiveW4 = 0;
            totalImplementationW4 = 0;
            return;
        }

        JSONArray dataArray = w4Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotW4          = 0.0;
        totalLiveW4           = 0.0;
        totalImplementationW4 = 0.0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject row = dataArray.getJSONObject(i);

            // Stage ismi
            String stage = row.optString("Sales Stage", "");

            // Count alanı bazen label_map yüzünden alternatif isimde olabilir, ikisini de kontrol et
            double count = row.optDouble("Customer Count",
                    row.optDouble("Customer Count_4aa1e1", 0.0));

            // Pilot/Liver'ı topla (bazı slice'larda "8-Pilot" / "9-Live" gelebilir, onu da yakala)
            if ("Pilot".equalsIgnoreCase(stage) || "8-Pilot".equalsIgnoreCase(stage)) {
                totalPilotW4 += count;
            }
            if ("Live".equalsIgnoreCase(stage) || "9-Live".equalsIgnoreCase(stage)) {
                totalLiveW4 += count;
            }
            // Implementation toplamı (güvenlik için "7-Implementation" varyantını da kontrol edebilirsin)
            if ("Implementation".equalsIgnoreCase(stage) || "7-Implementation".equalsIgnoreCase(stage)) {
                totalImplementationW4 += count;
            }
        }

        System.out.println("W4 Pilot toplam: " + totalPilotW4);
        System.out.println("W4 Live toplam: " + totalLiveW4);
        System.out.println("W4 Implementation toplam: " + totalImplementationW4);
    }

    double totalPilotW41;
    double totalLiveW41;
    double totalImplementationW41;

    @Given("The user send widget41 request")
    public void theUserSendWidget41Request() throws IOException {
        JSONObject w41Json = Requests.sendWidget41Request(); // varsa; yoksa kendi request'ini yap
        JSONArray data = w41Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotW41          = 0;
        totalLiveW41           = 0;
        totalImplementationW41 = 0;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.getJSONObject(i);

            // Bazı slice'larda kolon adları farklı gelebilir; hepsini güvenli okumaya çalışıyoruz.
            String stage = row.optString("Sales Stage",
                    row.optString("Sales Stage_5c2664",
                            row.optString("StageAggr", "")));

            // Metrik için de birkaç olası isim:
            double count = row.optDouble("Customer Count",
                    row.optDouble("Customer Count_4aa1e1",
                            row.optDouble("COUNT_DISTINCT(AssetId)", 0.0)));

            // Hem isimle (Pilot/Live/Implementation) hem de 8-Pilot/9-Live/7-Implementation etiketleriyle eşleştiriyoruz:
            if ("Pilot".equalsIgnoreCase(stage) || "8-Pilot".equalsIgnoreCase(stage)) {
                totalPilotW41 += count;
            } else if ("Live".equalsIgnoreCase(stage) || "9-Live".equalsIgnoreCase(stage)) {
                totalLiveW41 += count;
            } else if ("Implementation".equalsIgnoreCase(stage) || "7-Implementation".equalsIgnoreCase(stage)) {
                totalImplementationW41 += count;
            }
        }

        System.out.println("Pilot toplam Customer Count (W41): " + (long) totalPilotW41);
        System.out.println("Live toplam Customer Count (W41): " + (long) totalLiveW41);
        System.out.println("Implementation toplam Customer Count (W41): " + (long) totalImplementationW41);
    }

    @Then("The user verify scenario6")
    public void theUserVerifyScenario6() {
//        Assert.assertEquals("Senaryo 6 pilot sayılar eşleşmedi",
//                totalPilotW4, totalPilotW41, 0.01);
//
//        Assert.assertEquals("Senaryo 6 live sayılar eşleşmedi",
//                totalLiveW4, totalLiveW41, 0.01);

        Assert.assertEquals("Senaryo 6 implementation sayılar eşleşmedi",
                totalImplementationW4, totalImplementationW41, 0.01);
    }


    double totalPilotW42;
    double totalLiveW42;
    double totalImplW42;
    @Given("The user send widget42 request")
    public void theUserSendWidget42Request() throws IOException {
        JSONObject w42Json = Requests.sendWidget42Request();
        System.out.println("w42Json: " + w42Json);

        totalPilotW42 = 0.0;
        totalLiveW42  = 0.0;
        totalImplW42  = 0.0;

        if (w42Json == null) return;

        JSONArray results = w42Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // Kolon adlarını güvenli şekilde tespit et
        String pilotKey = "8-Pilot";
        String liveKey  = "9-Live";
        String implKey  = "7-Implementation";

        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String col = colnames.optString(i, "");
                String lower = col.toLowerCase();
                if (lower.contains("pilot"))          pilotKey = col;
                else if (lower.contains("live"))      liveKey  = col;
                else if (lower.contains("implement")) implKey  = col;
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            // null -> 0.0 olacak şekilde topla
            totalPilotW42 += row.isNull(pilotKey) ? 0.0 : row.optDouble(pilotKey, 0.0);
            totalLiveW42  += row.isNull(liveKey)  ? 0.0 : row.optDouble(liveKey,  0.0);
            totalImplW42  += row.isNull(implKey)  ? 0.0 : row.optDouble(implKey,  0.0);
        }

        System.out.println("W42 Pilot toplamı: " + totalPilotW42);
        System.out.println("W42 Live toplamı : " + totalLiveW42);
        System.out.println("W42 Implementation toplamı: " + totalImplW42);
    }

    double totalPilotW3SpecS7;
    double totalLiveW3SpecS7;
    double totalImplW3SpecS7;

    @Given("The user send widget3SpecialS7 request")
    public void theUserSendWidget3SpecialS7Request() throws IOException {
        JSONObject json = Requests.sendWidget3SpecialS7Request(); // widget3SpecialS7 için method
        System.out.println("w3SpecS7 json: " + json);

        totalPilotW3SpecS7 = 0.0;
        totalLiveW3SpecS7  = 0.0;
        totalImplW3SpecS7  = 0.0;

        if (json == null) return;

        JSONArray results = json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // Kolon adlarını güvenli şekilde tespit et
        String pilotKey = "8-Pilot";
        String liveKey  = "9-Live";
        String implKey  = "7-Implementation";

        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String col = colnames.optString(i, "");
                String lower = col.toLowerCase();
                if (lower.contains("pilot"))          pilotKey = col;
                else if (lower.contains("live"))      liveKey  = col;
                else if (lower.contains("implement")) implKey  = col;
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            totalPilotW3SpecS7 += row.isNull(pilotKey) ? 0.0 : row.optDouble(pilotKey, 0.0);
            totalLiveW3SpecS7  += row.isNull(liveKey)  ? 0.0 : row.optDouble(liveKey,  0.0);
            totalImplW3SpecS7  += row.isNull(implKey)  ? 0.0 : row.optDouble(implKey,  0.0);
        }

        System.out.println("W3SpecS7 Pilot toplamı: " + totalPilotW3SpecS7);
        System.out.println("W3SpecS7 Live toplamı : " + totalLiveW3SpecS7);
        System.out.println("W3SpecS7 Implementation toplamı: " + totalImplW3SpecS7);
    }

    @Then("The user verify scenario7")
    public void theUserVerifyScenario7() {
//        Assert.assertEquals("Senaryo 7 pilot sayılar eşleşmedi",
//                totalPilotW42, totalPilotW3SpecS7, 0.01);
//
//        Assert.assertEquals("Senaryo 7 live sayılar eşleşmedi",
//                totalLiveW42, totalLiveW3SpecS7, 0.01);

        Assert.assertEquals("Senaryo 7 implementation sayılar eşleşmedi",
                totalImplW42, totalImplW3SpecS7, 0.01);
    }

    double totalPilotW43;
    double totalLiveW43;
    double totalImplW43;

    @Given("The user send widget43 request")
    public void theUserSendWidget43Request() throws IOException {
        JSONObject w43Json = Requests.sendWidget43Request();
        System.out.println("w43Json: " + w43Json);

        totalPilotW43 = 0.0;
        totalLiveW43  = 0.0;
        totalImplW43  = 0.0;

        if (w43Json == null) return;

        JSONArray results = w43Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // --- Kolon adlarını güvenli şekilde tespit et (case-insensitive) ---
        String stageKey = "Sales Stage";
        String countKey = "Customer Count";

        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String col = colnames.optString(i, "");
                String lower = col.toLowerCase();
                if (lower.contains("stage")) {
                    stageKey = col;                  // örn: "Sales Stage"
                } else if (lower.contains("count")) {
                    countKey = col;                  // örn: "Customer Count"
                }
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String stage = row.optString(stageKey, "");
            double count = row.isNull(countKey) ? 0.0 : row.optDouble(countKey, 0.0);

            String s = stage.toLowerCase();
            if (s.contains("pilot")) {
                totalPilotW43 += count;
            } else if (s.contains("live")) {
                totalLiveW43 += count;
            } else if (s.contains("implement")) {
                totalImplW43 += count;
            }
        }

        System.out.println("W43 Pilot toplamı: " + totalPilotW43);
        System.out.println("W43 Live toplamı : " + totalLiveW43);
        System.out.println("W43 Implementation toplamı: " + totalImplW43);
    }


    double totalPilotW4SpecS8;
    double totalLiveW4SpecS8;
    double totalImplW4SpecS8;
    @Given("The user send widget4SpecialS8 request")
    public void theUserSendWidget4SpecialS8Request() throws IOException {
        JSONObject json = Requests.sendWidget4SpecialS8Request(); // W4SpecS8 için request methodu
        System.out.println("w4SpecS8 json: " + json);

        totalPilotW4SpecS8 = 0.0;
        totalLiveW4SpecS8  = 0.0;
        totalImplW4SpecS8  = 0.0;

        if (json == null) return;

        JSONArray results = json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // --- Kolon adlarını güvenli şekilde tespit et (case-insensitive) ---
        String stageKey = "Sales Stage";
        String countKey = "Customer Count";

        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String col = colnames.optString(i, "");
                String lower = col.toLowerCase();
                if (lower.contains("stage")) stageKey = col;     // örn: "Sales Stage"
                else if (lower.contains("count")) countKey = col; // örn: "Customer Count"
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String stage = row.optString(stageKey, "");
            double count = row.isNull(countKey) ? 0.0 : row.optDouble(countKey, 0.0);

            String s = stage.toLowerCase();
            if (s.contains("pilot")) {
                totalPilotW4SpecS8 += count;
            } else if (s.contains("live")) {
                totalLiveW4SpecS8 += count;
            } else if (s.contains("implement")) {
                totalImplW4SpecS8 += count;
            }
        }

        System.out.println("W4SpecS8 Pilot toplamı: " + totalPilotW4SpecS8);
        System.out.println("W4SpecS8 Live toplamı : " + totalLiveW4SpecS8);
        System.out.println("W4SpecS8 Implementation toplamı: " + totalImplW4SpecS8);
    }


    @Then("The user verify scenario8")
    public void theUserVerifyScenario8() {
//        Assert.assertEquals("Senaryo 8 pilot sayılar eşleşmedi",
//                totalPilotW43, totalPilotW4SpecS8, 0.01);
//
//        Assert.assertEquals("Senaryo 8 live sayılar eşleşmedi",
//                totalLiveW43, totalLiveW4SpecS8, 0.01);

        Assert.assertEquals("Senaryo 8 implementation sayılar eşleşmedi",
                totalImplW43, totalImplW4SpecS8, 0.01);
    }



    // === W1 Helpers & Totals (DÜZELTİLMİŞ) ===

    /** W1: "Sales Stage" ve "Customer Count" kolon adlarını dinamik bulur (sağlamlaştırılmış). */
    private static String[] resolveW1Keys(JSONObject resp) {
        String stageKey = "Sales Stage";
        String countKey = "Customer Count";
        try {
            if (resp == null) return new String[]{stageKey, countKey};
            JSONArray result = resp.optJSONArray("result");
            if (result == null || result.length() == 0) return new String[]{stageKey, countKey};
            JSONObject block0 = result.optJSONObject(0);
            if (block0 == null) return new String[]{stageKey, countKey};
            JSONArray colnames = block0.optJSONArray("colnames");
            if (colnames == null) return new String[]{stageKey, countKey};

            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                String lower = c.toLowerCase();
                if (lower.contains("stage")) stageKey = c;
                else if (lower.contains("count")) countKey = c;
            }
        } catch (Exception ignored) {}
        return new String[]{stageKey, countKey};
    }

    /** Normalize: lower-case, harf/rakam dışını temizle (eşleştirme için). */
    private static String norm(String s) {
        if (s == null) return "";
        return s.toLowerCase().replaceAll("[^a-z0-9]+", "");
    }

    /** İstenen etiket için alias/normalizasyon kümeleri (tam & kısmi eşleşme için). */
    private static List<String> aliasesFor(String wantedStageLabel) {
        String w = wantedStageLabel == null ? "" : wantedStageLabel.trim();

        // Normalized canonical keys
        String wn = norm(w);

        // Alias listesi: hem canonical, hem muhtemel varyantlar
        List<String> list = new ArrayList<>();
        list.add(wn);

        // Etikete göre bilinen varyantlar
        switch (w) {
            case "1-Prospect":
                list.add("prospect");
                list.add("1prospect");
                break;
            case "2-Lead":
                list.add("lead");
                list.add("2lead");
                break;
            case "3-Pitched":
                list.add("pitched");
                list.add("3pitched");
                list.add("pitch"); // olası kısaltmalar
                break;
            case "4-Contract":
                list.add("contract");
                list.add("4contract");
                break;
            case "5-Onb. Risk":
                list.add("onbrisk");
                list.add("onboardingrisk");
                list.add("5onbrisk");
                list.add("5onboardingrisk");
                break;
            case "6-Onb. Operation":
                list.add("onboperation");
                list.add("onboardingoperation");
                list.add("6onboperation");
                list.add("6onboardingoperation");
                break;
            case "7-Implementation":
                list.add("implementation");
                list.add("7implementation");
                break;
            case "8-Pilot":
                list.add("pilot");
                list.add("8pilot");
                break;
            case "9-Live":
                list.add("live");
                list.add("9live");
                break;
            case "10-Reject":
                list.add("reject");
                list.add("10reject");
                break;
            default:
                // Bilinmeyen için sadece normalized ek kalsın
                break;
        }
        // Tekrarsız hale getir
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /** W1: İstenen stage etiketine göre toplamı getir (alias + normalize + contains destekli). */
    public static double sumW1ByStage(JSONObject resp, String wantedStageLabel) {
        if (resp == null || wantedStageLabel == null) return 0.0;

        String[] keys = resolveW1Keys(resp);
        String stageKey = keys[0];
        String countKey = keys[1];

        List<String> targets = aliasesFor(wantedStageLabel);

        double sum = 0.0;
        try {
            JSONArray data = resp.getJSONArray("result").getJSONObject(0).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject row = data.getJSONObject(i);

                String stageRaw = row.optString(stageKey, "");
                String stageN = norm(stageRaw);

                boolean match = false;
                for (String t : targets) {
                    if (stageN.equals(t) || (t.length() >= 4 && stageN.contains(t))) {
                        match = true;
                        break;
                    }
                }

                if (match) {
                    double count = row.isNull(countKey) ? 0.0 : row.optDouble(countKey, 0.0);
                    sum += count;
                }
            }
        } catch (Exception ignored) {}
        return sum;
    }

    /** W1: Tüm temel stage toplamlarını tek seferde getir (6 & 7 dahil). */
    public static Map<String, Double> getAllStageTotalsFromW1(JSONObject resp) {
        String[] wanted = {
                "1-Prospect",
                "2-Lead",
                "3-Pitched",
                "4-Contract",
                "5-Onb. Risk",
                "6-Onb. Operation",
                "7-Implementation",
                "8-Pilot",
                "9-Live",
                "10-Reject"
        };
        Map<String, Double> totals = new LinkedHashMap<>();
        for (String w : wanted) {
            totals.put(w, sumW1ByStage(resp, w));
        }
        return totals;
    }

    // === Step alanları ===
    double totalProspectW1, totalLeadW1, totalPitchedW1, totalContractW1,
            totalOnbRiskW1, totalPilotW1, totalLiveW1, totalRejectW1,
            totalOnbOperationW1, totalImplW1;

    @Given("The user send widget1 request")
    public void theUserSendWidget1Request() throws Exception {
        JSONObject w1Json = Requests.sendWidget1Request();
        System.out.println("w1Json: " + w1Json);

        Map<String, Double> totals = getAllStageTotalsFromW1(w1Json);

        totalProspectW1     = totals.getOrDefault("1-Prospect", 0.0);
        totalLeadW1         = totals.getOrDefault("2-Lead", 0.0);
        totalPitchedW1      = totals.getOrDefault("3-Pitched", 0.0);
        totalContractW1     = totals.getOrDefault("4-Contract", 0.0);
        totalOnbRiskW1      = totals.getOrDefault("5-Onb. Risk", 0.0);
        totalOnbOperationW1 = totals.getOrDefault("6-Onb. Operation", 0.0);
        totalImplW1         = totals.getOrDefault("7-Implementation", 0.0);
        totalPilotW1        = totals.getOrDefault("8-Pilot", 0.0);
        totalLiveW1         = totals.getOrDefault("9-Live", 0.0); // Query Live'ı dışladıysa 0 olur
        totalRejectW1       = totals.getOrDefault("10-Reject", 0.0);

        System.out.println("W1 1-Prospect      : " + totalProspectW1);
        System.out.println("W1 2-Lead          : " + totalLeadW1);
        System.out.println("W1 3-Pitched       : " + totalPitchedW1);
        System.out.println("W1 4-Contract      : " + totalContractW1);
        System.out.println("W1 5-Onb. Risk     : " + totalOnbRiskW1);
        System.out.println("W1 6-Onb. Operation: " + totalOnbOperationW1);
        System.out.println("W1 7-Implementation: " + totalImplW1);
        System.out.println("W1 8-Pilot         : " + totalPilotW1);
        System.out.println("W1 9-Live          : " + totalLiveW1);
        System.out.println("W1 10-Reject       : " + totalRejectW1);
    }

    @Then("The user verify scenario9")
    public void theUserVerifyScenario9() {

        Assert.assertEquals("Senaryo 9 prospect sayılar eşleşmedi",
                totalProspectW1, totalProspectW3, 0.01);

        Assert.assertEquals("Senaryo 9 lead sayılar eşleşmedi",
                totalLeadW1, totalLeadW3, 0.01);

        Assert.assertEquals("Senaryo 9 pitched sayılar eşleşmedi",
                totalPitchedW1, totalPitchedW3, 0.01);

        Assert.assertEquals("Senaryo 9 contract sayılar eşleşmedi",
                totalContractW1, totalContractW3, 0.01);

        Assert.assertEquals("Senaryo 9 risk sayılar eşleşmedi",
                totalOnbRiskW1, totalOnbRiskW3, 0.01);

        Assert.assertEquals("Senaryo 9 operation sayılar eşleşmedi",
                totalOnbOperationW1, totalOnbOperationW3, 0.01);

        Assert.assertEquals("Senaryo 9 implementation sayılar eşleşmedi",
                totalImplW1, totalImplW3, 0.01);

        Assert.assertEquals("Senaryo 9 pilot sayılar eşleşmedi",
                totalPilotW1, totalPilotW3, 0.01);

//        Assert.assertEquals("Senaryo 9 live sayılar eşleşmedi",
//                totalLiveW1, totalLiveW3, 0.01);

//        Assert.assertEquals("Senaryo 9 reject sayılar eşleşmedi",
//                totalRejectW1, totalRejectW3, 0.01);
    }



    // === W5: SalesRep bazında 1..10 stage değerlerini çıkar ===

    /** Kanonik sıra: 1..10 */
    private static final String[] W5_CANONICAL = {
            "1-Prospect",
            "2-Lead",
            "3-Pitched",
            "4-Contract",
            "5-Onb. Risk",
            "6-Onb. Operation",
            "7-Implementation",
            "8-Pilot",
            "9-Live",
            "10-Reject"
    };

    /** Basit normalize: lower + harf/rakam dışını temizle */
    private static String n(String s) {
        if (s == null) return "";
        return s.toLowerCase().replaceAll("[^a-z0-9]+", "");
    }

    /** Bir kanonik label için olası alias anahtarları */
    private static List<String> aliases(String canonical) {
        String c = canonical == null ? "" : canonical.trim();
        String cn = n(c);
        List<String> list = new ArrayList<>();
        list.add(cn);
        switch (c) {
            case "1-Prospect":        list.add("prospect"); break;
            case "2-Lead":            list.add("lead"); break;
            case "3-Pitched":         list.add("pitched"); list.add("pitch"); break;
            case "4-Contract":        list.add("contract"); break;
            case "5-Onb. Risk":       list.add("onbrisk"); list.add("onboardingrisk"); break;
            case "6-Onb. Operation":  list.add("onboperation"); list.add("onboardingoperation"); break;
            case "7-Implementation":  list.add("implementation"); break;
            case "8-Pilot":           list.add("pilot"); break;
            case "9-Live":            list.add("live"); break;
            case "10-Reject":         list.add("reject"); break;
        }
        return new ArrayList<>(new LinkedHashSet<>(list));
    }


    /** W5: "SalesRep" kolon adını ve bulunan stage sütunlarını çöz (kanonik -> gerçek ad) */
    private static class W5Keys {
        String salesRepKey = "SalesRep";
        Map<String, String> stageColByCanonical = new LinkedHashMap<>(); // "8-Pilot" -> "8-Pilot" (veya gerçek ad)
    }

    private static W5Keys resolveW5Keys(JSONObject resp) {
        W5Keys keys = new W5Keys();
        try {
            JSONArray result = resp.optJSONArray("result");
            if (result == null || result.length() == 0) return keys;
            JSONObject block0 = result.optJSONObject(0);
            if (block0 == null) return keys;

            JSONArray colnames = block0.optJSONArray("colnames");
            if (colnames == null) return keys;

            // Önce SalesRep kolonunu bul
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                if (n(c).contains("salesrep")) {
                    keys.salesRepKey = c;
                    break;
                }
            }

            // Stage sütunlarını (var olanları) kanoniklere eşle
            // (ör. sadece 2-Lead,3-Pitched,4-Contract,5-Onb. Risk,8-Pilot varsa onları eşler)
            List<String> allCols = new ArrayList<>();
            for (int i = 0; i < colnames.length(); i++) {
                allCols.add(colnames.optString(i, ""));
            }

            for (String canonical : W5_CANONICAL) {
                List<String> want = aliases(canonical);
                String matched = null;
                // En iyi eşleşmeyi bul: önce tam, sonra normalize eşit, sonra contains
                for (String col : allCols) {
                    String nn = n(col);
                    if (col.equals(canonical)) { matched = col; break; }
                    if (nn.equals(n(canonical))) { matched = col; break; }
                    for (String a : want) {
                        if (nn.equals(a) || (a.length() >= 4 && nn.contains(a))) { matched = col; break; }
                    }
                    if (matched != null) break;
                }
                if (matched != null && !n(matched).contains("salesrep")) {
                    keys.stageColByCanonical.put(canonical, matched);
                } else {
                    // Kolon hiç yoksa eşleme yapma (okuma sırasında 0 olarak ele alınacak)
                    keys.stageColByCanonical.put(canonical, null);
                }
            }
        } catch (Exception ignored) {}
        return keys;
    }

    /**
     * W5 parse: Her SalesRep için 1..10 tüm stage değerlerini döndürür.
     * Sonuç: Map< SalesRep, Map<CanonicalStage, Double> >
     */
    public static Map<String, Map<String, Double>> parseW5PerRep(JSONObject resp) {
        Map<String, Map<String, Double>> out = new LinkedHashMap<>();
        if (resp == null) return out;

        W5Keys keys = resolveW5Keys(resp);

        try {
            JSONArray data = resp.getJSONArray("result").getJSONObject(0).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject row = data.optJSONObject(i);
                if (row == null) continue;

                String rep = row.optString(keys.salesRepKey, "UNKNOWN");

                // Başlangıçta tüm kanonikler 0
                Map<String, Double> stages = out.computeIfAbsent(rep, r -> {
                    Map<String, Double> m = new LinkedHashMap<>();
                    for (String k : W5_CANONICAL) m.put(k, 0.0);
                    return m;
                });

                // Var olan her stage sütunundan değeri al, yoksa 0 bırak
                for (String canonical : W5_CANONICAL) {
                    String realCol = keys.stageColByCanonical.get(canonical);
                    double val = 0.0;
                    if (realCol != null && row.has(realCol) && !row.isNull(realCol)) {
                        // Bu JSON pivot’unda sayılar TRY (double). Null ise 0.
                        val = row.optDouble(realCol, 0.0);
                    }
                    // Mevcut üzerine ekleyelim (satır bir rep’in tek kaydı olsa da güvenli)
                    stages.put(canonical, stages.getOrDefault(canonical, 0.0) + val);
                }
            }
        } catch (Exception ignored) {}

        return out;
    }

    // === Örnek kullanım (Step) ===
    Map<String, Map<String, Double>> w5PerRep = new LinkedHashMap<>();

    @Given("The user send widget5 request")
    public void theUserSendWidget5Request() throws Exception {
        JSONObject w5Json = Requests.sendWidget5Request(); // W5 için kendi request metodun
        System.out.println("w5Json: " + w5Json);

        try {
            JSONArray resultArr = w5Json.optJSONArray("result");
            if (resultArr == null || resultArr.length() == 0) {
                System.out.println("result array boş geldi");
                return;
            }

            JSONObject block = resultArr.optJSONObject(0);
            if (block == null) {
                System.out.println("result[0] null");
                return;
            }

            // Kolon isimlerinden stage kolonlarını çıkar (SalesRep hariç)
            JSONArray colnames = block.optJSONArray("colnames"); // ["SalesRep","10-Reject",...]
            List<String> stageCols = new ArrayList<>();
            if (colnames != null) {
                for (int i = 0; i < colnames.length(); i++) {
                    String col = colnames.optString(i, "");
                    if (!"SalesRep".equalsIgnoreCase(col) && !col.isEmpty()) {
                        stageCols.add(col);
                    }
                }
            }

            JSONArray dataArr = block.optJSONArray("data");
            if (dataArr == null) {
                System.out.println("data array null");
                return;
            }

            for (int i = 0; i < dataArr.length(); i++) {
                JSONObject row = dataArr.optJSONObject(i);
                if (row == null) continue;

                String rep = row.optString("SalesRep", "").trim();
                if (rep.isEmpty()) continue;

                Map<String, Double> perStage = w5PerRep.computeIfAbsent(rep, k -> new LinkedHashMap<>());

                for (String stageCol : stageCols) {
                    // W5 kolon adını, W6 ile kıyaslamada kullanışlı olacak kanonik ada çevir
                    String canonicalStage;
                    if ("3-Pitched".equalsIgnoreCase(stageCol)) {
                        canonicalStage = "Pitched";
                    } else if ("7-Implementation".equalsIgnoreCase(stageCol)) {
                        canonicalStage = "Implementation";
                    } else if ("8-Pilot".equalsIgnoreCase(stageCol)) {
                        canonicalStage = "Pilot";
                    } else if ("5-Onb. Risk".equalsIgnoreCase(stageCol)) {
                        // W6'da "Onboarding" geçtiği için bunu "Onboarding" olarak normalize edelim
                        canonicalStage = "Onboarding";
                    } else if ("10-Reject".equalsIgnoreCase(stageCol)) {
                        canonicalStage = "Reject";
                    } else if ("2-Lead".equalsIgnoreCase(stageCol)) {
                        canonicalStage = "Lead";
                    } else if ("4-Contract".equalsIgnoreCase(stageCol)) {
                        canonicalStage = "Contract";
                    } else {
                        // Beklenmedik kolon ismi gelirse olduğu gibi kullan
                        canonicalStage = stageCol;
                    }

                    // Değeri oku (null ise map'e null koy; 0 ile eşit sayma mantığını zaten karşılaştırmada yaptık)
                    Double val = null;
                    Object raw = row.opt(stageCol);
                    if (raw != null && raw != JSONObject.NULL) {
                        // Bazı durumlarda sayılar string gelebilir, güvenli parse et
                        try {
                            // "0E-10" gibi bilimsel gösterimler için String'e çevirip parse etmek daha güvenli
                            val = Double.valueOf(raw.toString());
                        } catch (Exception ignore) {
                            // parse olmazsa null bırak
                        }
                    }

                    perStage.put(canonicalStage, val);
                }
            }

            // --- İsteğe bağlı: hepsini konsola dökelim ---
            for (Map.Entry<String, Map<String, Double>> repEntry : w5PerRep.entrySet()) {
                System.out.println("\n=== " + repEntry.getKey() + " ===");
                for (Map.Entry<String, Double> st : repEntry.getValue().entrySet()) {
                    System.out.println(st.getKey() + " -> " + st.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // === Değişmedi: W6_CANONICAL ve n(String) aynı kalsın ===

    /** Kanonik sıra: 1..10 */
    private static final String[] W6_CANONICAL = {
            "1-Prospect",
            "2-Lead",
            "3-Pitched",
            "4-Contract",
            "5-Onb. Risk",
            "6-Onb. Operation",
            "7-Implementation",
            "8-Pilot",
            "9-Live",
            "10-Reject"
    };


    // --- Güçlendirilmiş kolon çözümleme (değişmedi) ---
    private static class W6Keys {
        String stageKey = "Sales Stage";
        String repKey   = "SalesRep";
        String countKey = "Customer Count";
    }
    private static W6Keys resolveW6Keys(JSONObject resp) {
        W6Keys k = new W6Keys();
        try {
            JSONArray result = resp.optJSONArray("result");
            if (result == null || result.length() == 0) return k;
            JSONObject block0 = result.optJSONObject(0);
            if (block0 == null) return k;

            JSONArray colnames = block0.optJSONArray("colnames");
            if (colnames == null) return k;

            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                String nn = n(c);
                if (nn.contains("stage"))                 k.stageKey = c;
                else if (nn.contains("salesrep"))         k.repKey   = c;
                else if (nn.contains("count")
                        || nn.contains("volume")
                        || nn.contains("try"))             k.countKey = c;
            }
        } catch (Exception ignored) {}
        return k;
    }

    // --- GÜNCEL: Stage’i kanoniğe çok sağlam map et ---
    private static String mapStageToCanonical(String stageRaw) {
        if (stageRaw == null) return null;

        String s = stageRaw.trim();

        // 0) Direkt N-Stage formatı (örn: "7-Implementation", "8-Pilot") -> önce bunu yakala
        //    (başta yakalarsak "7-Implementation" zaten doğrudan döner)
        for (String can : W6_CANONICAL) {
            // case-insensitive eşle
            if (s.equalsIgnoreCase(can)) return can;

            // başı/sounda boşluk/ufak sapmalar için gevşek kontrol
            if (s.toLowerCase().startsWith(can.toLowerCase())) return can;
            if (s.toLowerCase().contains(can.toLowerCase()))   return can;
        }

        // 1) hızlı normalizasyon (a-z0-9 dışını at, lowercase)
        String sn = n(s);

        // 2) Kelime kökü varyantları
        // Reject
        if (sn.contains("reject") || sn.contains("ret")) return "10-Reject";

        // 1..4
        if (sn.contains("prospect"))  return "1-Prospect";
        // "lead" kelime olarak (leadtime vs’ye takılmayalım diye \blead\b yerine basit contains; normalizasyon zaten çok sınırlı)
        if (sn.contains("lead"))      return "2-Lead";
        if (sn.contains("pitch"))     return "3-Pitched";
        if (sn.contains("contract"))  return "4-Contract";

        // 5-6 Onboarding varyantları
        if (sn.contains("onboarding") || sn.startsWith("onb")) {
            if (sn.contains("operation") || sn.contains("operasyon")) return "6-Onb. Operation";
            if (sn.contains("risk"))                                  return "5-Onb. Risk";
            return "5-Onb. Risk"; // generic onboarding
        }

        // 7 Implementation (net yakalama: implementation / implement / impl)
        if (sn.equals("implementation")
                || sn.startsWith("implementation")
                || sn.contains("implementation")
                || sn.startsWith("implement")
                || sn.contains("implement")
                || sn.startsWith("impl")
                || sn.contains("impl")) {
            return "7-Implementation";
        }

        // 8..9
        if (sn.contains("pilot")) return "8-Pilot";
        if (sn.contains("live") || sn.contains("growth")) return "9-Live";

        return null;
    }

    // --- Değişmedi: sayısal güvenli okuma ---
    private static double safeDouble(JSONObject row, String key) {
        if (row == null || key == null || row.isNull(key)) return 0.0;
        try {
            return row.optDouble(key, 0.0);
        } catch (Exception ignored) {
            String s = row.optString(key, "0").replace(",", "");
            try { return Double.parseDouble(s); } catch (Exception ig2) { return 0.0; }
        }
    }

    // --- FIX 2: parse sırasında hedef satırları debugla ---
    public static Map<String, Map<String, Double>> parseW6PerRep(JSONObject resp) {
        Map<String, Map<String, Double>> out = new LinkedHashMap<>();
        if (resp == null) return out;

        W6Keys keys = resolveW6Keys(resp);

        try {
            JSONArray data = resp.getJSONArray("result").getJSONObject(0).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject row = data.optJSONObject(i);
                if (row == null) continue;

                String rep      = row.optString(keys.repKey, "UNKNOWN");
                String stageRaw = row.optString(keys.stageKey, "");
                String canonical = mapStageToCanonical(stageRaw);

                if (canonical == null) {
                    // Gerekirse aç: System.out.println("[W6] Eşleşmedi: '" + stageRaw + "'");
                    continue;
                }

                // DEBUG: Emre Uzun + Implementation satırını açıkça gör
                if ("Emre Uzun".equals(rep) && "7-Implementation".equals(canonical)) {
                    System.out.println("DEBUG | Emre Uzun Implementation satırı bulundu. RawStage='"
                            + stageRaw + "', val=" + safeDouble(row, keys.countKey));
                }

                Map<String, Double> stages = out.computeIfAbsent(rep, r -> {
                    Map<String, Double> m = new LinkedHashMap<>();
                    for (String k : W6_CANONICAL) m.put(k, 0.0);
                    return m;
                });

                double val = safeDouble(row, keys.countKey);
                stages.put(canonical, stages.get(canonical) + val);
            }
        } catch (Exception e) {
            System.out.println("[W6] parse error: " + e.getMessage());
        }

        for (Map<String, Double> st : out.values()) {
            for (String k : W6_CANONICAL) st.putIfAbsent(k, 0.0);
        }
        return out;
    }
    /**
     * W6 helper: SalesRep + Stage kombinasyonu için değeri getirir.
     * Stage alias'larını ("Implementation", "Pilot", "Reject" vb.) da destekler.
     */
    public static double getW6Value(Map<String, Map<String, Double>> perRep, String rep, String stageAlias) {
        if (perRep == null || rep == null || stageAlias == null) return 0.0;
        Map<String, Double> m = perRep.get(rep);
        if (m == null) return 0.0;

        // 1️⃣ önce doğrudan tam anahtarı dene
        Double d = m.get(stageAlias);
        if (d != null) return d;

        // 2️⃣ alias olarak stage ismini normalize edip kanonik karşılığını ara
        String alias = stageAlias.trim().toLowerCase();

        if (alias.equals("prospect"))        return m.getOrDefault("1-Prospect", 0.0);
        if (alias.equals("lead"))            return m.getOrDefault("2-Lead", 0.0);
        if (alias.startsWith("pitch"))       return m.getOrDefault("3-Pitched", 0.0);
        if (alias.equals("contract"))        return m.getOrDefault("4-Contract", 0.0);
        if (alias.contains("onb") && alias.contains("risk"))
            return m.getOrDefault("5-Onb. Risk", 0.0);
        if (alias.contains("onb") && alias.contains("operation"))
            return m.getOrDefault("6-Onb. Operation", 0.0);
        if (alias.contains("implementation"))
            return m.getOrDefault("7-Implementation", 0.0);
        if (alias.contains("pilot"))         return m.getOrDefault("8-Pilot", 0.0);
        if (alias.contains("live"))          return m.getOrDefault("9-Live", 0.0);
        if (alias.contains("reject") || alias.contains("ret"))
            return m.getOrDefault("10-Reject", 0.0);

        return 0.0;
    }


    JSONObject lastW6Json;
    Map<String, Map<String, Double>> w6PerRep = new LinkedHashMap<>();

    @Given("The user send widget6 request")
    public void theUserSendWidget6Request() throws Exception {
        lastW6Json = Requests.sendWidget6Request();
        System.out.println("w6Json: " + lastW6Json);

        try {
            JSONArray resultArr = lastW6Json.optJSONArray("result");
            if (resultArr == null || resultArr.isEmpty()) {
                System.out.println("result array boş geldi");
                return;
            }

            JSONObject block = resultArr.optJSONObject(0);
            if (block == null) {
                System.out.println("result[0] null");
                return;
            }

            JSONArray dataArr = block.optJSONArray("data");
            if (dataArr == null) {
                System.out.println("data array null");
                return;
            }

            for (int i = 0; i < dataArr.length(); i++) {
                JSONObject row = dataArr.optJSONObject(i);
                if (row == null) continue;

                String rep = row.optString("SalesRep", "").trim();
                String stage = row.optString("Sales Stage", "").trim();
                double count = row.optDouble("Customer Count", 0);

                if (rep.isEmpty() || stage.isEmpty()) continue;

                // İç map'i oluştur veya mevcut olanı al
                Map<String, Double> repMap = w6PerRep.computeIfAbsent(rep, k -> new LinkedHashMap<>());
                repMap.put(stage, count);
            }

            // --- Tüm rep'leri ve stage değerlerini yazdır ---
            for (Map.Entry<String, Map<String, Double>> entry : w6PerRep.entrySet()) {
                String rep = entry.getKey();
                System.out.println("\n=== " + rep + " ===");
                for (Map.Entry<String, Double> stageEntry : entry.getValue().entrySet()) {
                    System.out.println(stageEntry.getKey() + " → " + stageEntry.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean areMapsEqual(Map<String, Map<String, Double>> w5PerRep,
                                       Map<String, Map<String, Double>> w6PerRep) {

        final double EPS = 0.01; // izin verilen maksimum fark

        System.out.println("w5 size: " + w5PerRep.size());
        System.out.println("w6 size: " + w6PerRep.size());
//  if (w5PerRep.size() != w6PerRep.size()) return false;

        for (String rep : w6PerRep.keySet()) {
            Map<String, Double> inner5 = w5PerRep.get(rep);
            Map<String, Double> inner6 = w6PerRep.get(rep);

            if (inner5 == null) {
                System.out.println("Eksik rep bulundu (w5'te yok): " + rep);
                return false;
            }
            if (inner6 == null) {
                System.out.println("Eksik rep bulundu (w6'da yok): " + rep);
                return false;
            }

            for (String stage : inner5.keySet()) {
                Double v1 = inner5.get(stage);
                Double v2 = inner6.get(stage);

                // null'ları 0.0 gibi ele al
                double d1 = (v1 == null ? 0.0 : v1);
                double d2 = (v2 == null ? 0.0 : v2);

                double diff = Math.abs(d1 - d2);
                boolean equal = diff <= EPS; // 0.01 ve altını eşit kabul et

                if (!equal) {
                    System.out.printf("Farklı değer bulundu rep: %s | stage: %s -> %s vs %s (diff=%.5f)%n",
                            rep, stage, d1, d2, diff);
                    return false;
                }
            }
        }

        return true;
    }



    @Then("The user verify scenario10")
    public void theUserVerifyScenario10() {
        boolean scenario10 = areMapsEqual(w6PerRep,w5PerRep);

        Assert.assertTrue("s10 fail",scenario10);
    }

    // W16 — week → posVolume map
    Map<String, Double> w16WeekPosMap;

    @Given("The user send widget16 request")
    public void theUserSendWidget16Request() throws Exception {
        JSONObject w16Json = Requests.sendWidget16Request();
        System.out.println("w16Json: " + w16Json);

        w16WeekPosMap = new LinkedHashMap<>(); // görünüm sırasını koru

        if (w16Json == null) return;
        JSONArray results = w16Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // Kolon adlarını dinamik çöz
        String weekKey = "Week";
        String posKey  = "POS Volume (TL)";
        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                String lc = c.toLowerCase();
                if (lc.equals("week")) weekKey = c;
                // pos volume (tl) için esnek eşleşme
                if (lc.contains("pos") && (lc.contains("vol") || lc.contains("volume"))) posKey = c;
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String week = row.optString(weekKey, "");
            double pos  = 0.0;

            if (!row.isNull(posKey)) {
                try {
                    pos = row.optDouble(posKey, 0.0);
                    if (Double.isNaN(pos)) {
                        // sayı string gelirse (örn. "0E-10" vb.)
                        String s = row.optString(posKey, "0").replace(",", "");
                        pos = Double.parseDouble(s);
                    }
                } catch (Exception ignored) {}
            }

            if (!week.isEmpty()) {
                w16WeekPosMap.put(week, pos);
            }
        }

        // İsteğe bağlı: konsola yazdır
        for (Map.Entry<String, Double> e : w16WeekPosMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    // W17 — week → posVolume map (Week, POS Volume (TL))
    Map<String, Double> w17WeekPosMap;

    @Given("The user send widget17 request")
    public void theUserSendWidget17Request() throws Exception {
        JSONObject w17Json = Requests.sendWidget17Request();
        System.out.println("w17Json: " + w17Json);

        w17WeekPosMap = new LinkedHashMap<>(); // sıra korunur

        if (w17Json == null) return;
        JSONArray results = w17Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // Kolon adlarını dinamik tespit et
        String weekKey = "Week";
        String posKey  = "POS Volume (TL)";
        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                String lc = c.toLowerCase();
                if (lc.contains("week")) weekKey = c;
                if (lc.contains("pos") && (lc.contains("vol") || lc.contains("volume"))) posKey = c;
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String week = row.optString(weekKey, "");
            double pos  = 0.0;

            if (!row.isNull(posKey)) {
                try {
                    pos = row.optDouble(posKey, 0.0);
                    if (Double.isNaN(pos)) {
                        String s = row.optString(posKey, "0").replace(",", "");
                        pos = Double.parseDouble(s);
                    }
                } catch (Exception ignored) {}
            }

            if (!week.isEmpty()) {
                w17WeekPosMap.put(week, pos);
            }
        }

        // Opsiyonel: konsola yaz
        for (Map.Entry<String, Double> e : w17WeekPosMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    public static boolean areMapsEqualWithTolerance(Map<String, Double> map1,
                                                    Map<String, Double> map2,
                                                    double tolerance) {
        if (map1.size() != map2.size()) return false;

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                System.out.println("Eksik key: " + key);
                return false;
            }

            double val1 = map1.get(key);
            double val2 = map2.get(key);

            if (Math.abs(val1 - val2) > tolerance) {
                System.out.printf("Fark bulundu: %s -> %.4f vs %.4f%n", key, val1, val2);
                return false;
            }
        }

        return true;
    }



    @Then("The user verify scenario11")
    public void theUserVerifyScenario11() {
        boolean areEqual = areMapsEqualWithTolerance(w16WeekPosMap,w17WeekPosMap,0.01);
        Assert.assertTrue(areEqual);
    }

    // W18 — months → posVolume (msp)
    Map<String, Double> w18Msp;

    @Given("The user send widget18 request")
    public void theUserSendWidget18Request() throws Exception {
        JSONObject w18Json = Requests.sendWidget18Request();
        System.out.println("w18Json: " + w18Json);

        w18Msp = new LinkedHashMap<>(); // ekranda gelen sıralamayı koru

        if (w18Json == null) return;
        JSONArray results = w18Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // Kolon adlarını dinamik tespit et
        String monthKey = "Months";
        String posKey   = "POS Volume (TL)";
        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String c  = colnames.optString(i, "");
                String lc = c.toLowerCase();
                if (lc.contains("months")) monthKey = c; // "Months" / "month_label" varyasyonlarına dayanıklı
                if (lc.contains("pos") && (lc.contains("vol") || lc.contains("volume"))) posKey = c;
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String month = row.optString(monthKey, "");
            double pos   = 0.0;

            if (!row.isNull(posKey)) {
                try {
                    pos = row.optDouble(posKey, 0.0);
                    if (Double.isNaN(pos)) {
                        String s = row.optString(posKey, "0").replace(",", "");
                        pos = Double.parseDouble(s);
                    }
                } catch (Exception ignored) {}
            }

            if (!month.isEmpty()) {
                w18Msp.put(month, pos);
            }
        }

        // Opsiyonel: konsola yaz
        for (Map.Entry<String, Double> e : w18Msp.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    // W19 — months → posVolume map
    Map<String, Double> w19Msp;

    @Given("The user send widget19 request")
    public void theUserSendWidget19Request() throws Exception {
        JSONObject w19Json = Requests.sendWidget19Request();
        System.out.println("w19Json: " + w19Json);

        w19Msp = new LinkedHashMap<>(); // ekrandaki sıralamayı koru

        if (w19Json == null) return;
        JSONArray results = w19Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // Kolon adlarını dinamik çöz (Month / POS Volume (TL))
        String monthKey = "Month";
        String posKey   = "POS Volume (TL)";
        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String c  = colnames.optString(i, "");
                String lc = c.toLowerCase();
                if (lc.contains("month")) monthKey = c;  // "Month", "Months", "month_label" varyasyonlarına dayanıklı
                if (lc.contains("pos") && (lc.contains("vol") || lc.contains("volume"))) posKey = c;
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String month = row.optString(monthKey, "");
            double pos   = 0.0;

            if (!row.isNull(posKey)) {
                try {
                    pos = row.optDouble(posKey, 0.0);
                    if (Double.isNaN(pos)) {
                        String s = row.optString(posKey, "0").replace(",", "");
                        pos = Double.parseDouble(s);
                    }
                } catch (Exception ignored) {}
            }

            if (!month.isEmpty()) {
                w19Msp.put(month, pos);
            }
        }

        // Opsiyonel: konsola yazdır
        for (Map.Entry<String, Double> e : w19Msp.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    @Then("The user verify scenario12")
    public void theUserVerifyScenario12() {
        boolean areEqual = areMapsEqualWithTolerance(w18Msp,w19Msp,0.01);
        Assert.assertTrue(areEqual);
    }



    // --- yardımcılar ---
    private static String resolveMonthStartKey(JSONObject block0) {
        String key = "month_start";
        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames != null) {
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                if (c.toLowerCase().contains("month_start")) {
                    key = c;
                    break;
                }
            }
        }
        return key;
    }

    private static String toMonthLabelEn(double epochMs) {
        // ClickHouse epoch ms → "MMMM yyyy", EN
        long ms = (long) epochMs;
        return java.time.Instant.ofEpochMilli(ms)
                .atZone(java.time.ZoneId.of("Europe/Istanbul"))
                .format(java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy", java.util.Locale.ENGLISH));
    }

    private static Double toDoubleSafe(Object o) {
        if (o == null) return null;
        if (o instanceof Number) {
            double v = ((Number) o).doubleValue();
            // 0E-10 gibi bilimsel “sıfır”ları temizle
            if (Math.abs(v) < 1e-9) v = 0.0;
            return v;
        }
        if (o instanceof String) {
            String s = ((String) o).trim().replace(",", "");
            if (s.isEmpty()) return null;
            try {
                double v = Double.parseDouble(s);
                if (Math.abs(v) < 1e-9) v = 0.0;
                return v;
            } catch (Exception ignored) {
                return null;
            }
        }
        return null;
    }

    // W22 — Map<String, Map<String, Double>>
// Dış key: "MMMM yyyy" (EN) ay etiketi
// İç key: company_name (pivot kolon adı), value: Volume(TL)

    Map<String, Map<String, Double>> w22Map;

    @Given("The user send widget22 request")
    public void theUserSendWidget22Request() throws Exception {
        JSONObject w22Json = Requests.sendWidget22Request();
        System.out.println("w22Json: " + w22Json);

        w22Map = new LinkedHashMap<>(); // ay sırasını koru

        if (w22Json == null) return;
        JSONArray results = w22Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        // month_start kolonunu ve tüm merchant (pivot) kolonlarını belirle
        String monthStartKey = resolveMonthStartKey(block0);
        JSONArray colnames = block0.optJSONArray("colnames");
        if (colnames == null || colnames.length() == 0) return;

        // İlk kolon month_start, geri kalanların hepsi firma isimleri (pivot)
        List<String> merchantCols = new ArrayList<>();
        for (int i = 0; i < colnames.length(); i++) {
            String c = colnames.optString(i, "");
            if (!c.equals(monthStartKey)) {
                merchantCols.add(c);
            }
        }

        JSONArray data = block0.optJSONArray("data");
        if (data == null) return;

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            // Ay etiketi (EN)
            Double msVal = toDoubleSafe(row.opt(monthStartKey));
            if (msVal == null) continue;
            String monthLabel = toMonthLabelEn(msVal).split(" ")[0];
            System.out.println("monthLabel: " + monthLabel);

            Map<String, Double> companyToValue = new LinkedHashMap<>();
            for (String col : merchantCols) {
                if (row.isNull(col)) continue;
                Double val = toDoubleSafe(row.opt(col));
                if (val == null) continue;
                // İstersen null olmayanları ve 0 olmayanları koy; 0’ları istersen atla:
                // if (val == 0.0) continue;
                companyToValue.put(col, val);
            }

            w22Map.put(monthLabel, companyToValue);
        }

        // Opsiyonel: konsola kısa çıktı
        for (Map.Entry<String, Map<String, Double>> e : w22Map.entrySet()) {
            System.out.println("=== " + e.getKey() + " ===");
            e.getValue().forEach((k, v) -> System.out.println(k + " -> " + v));
        }
    }


    // V23 — Map<String, Map<String, Double>>
// Dış key: Month (örn. "August")
// İç key: Merchant Name, value: Volume (TL)

    Map<String, Map<String, Double>> w23Map;

    // --- yardımcılar ---
    private static String resolveKey(JSONArray colnames, String... hints) {
        // colnames içinden ipucuna (hints) en iyi uyan kolonu bul
        String fallback = colnames.optString(0, "");
        for (String hint : hints) {
            String h = hint.toLowerCase();
            for (int i = 0; i < colnames.length(); i++) {
                String c = colnames.optString(i, "");
                if (c.toLowerCase().contains(h)) return c;
            }
        }
        return fallback;
    }

//    private static Double toDoubleSafe(Object o) {
//        if (o == null) return null;
//        if (o instanceof Number) {
//            double v = ((Number) o).doubleValue();
//            if (Math.abs(v) < 1e-9) v = 0.0; // 0E-10 vb.
//            return v;
//        }
//        if (o instanceof String) {
//            String s = ((String) o).trim().replace(",", "");
//            if (s.isEmpty()) return null;
//            try {
//                double v = Double.parseDouble(s);
//                if (Math.abs(v) < 1e-9) v = 0.0;
//                return v;
//            } catch (Exception ignored) { return null; }
//        }
//        return null;
//    }

    @Given("The user send widget23 request")
    public void theUserSendW23Request() throws Exception {
        JSONObject v23Json = Requests.sendWidget23Request(); // kendi send metodun
        System.out.println("v23Json: " + v23Json);

        w23Map = new LinkedHashMap<>(); // gelen sırayı koru

        if (v23Json == null) return;
        JSONArray results = v23Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        JSONArray colnames = block0.optJSONArray("colnames");
        JSONArray data     = block0.optJSONArray("data");
        if (colnames == null || data == null) return;

        // Kolon adlarını dinamik çöz (çeşitli ad varyantlarına dayanıklı)
        String monthKey    = resolveKey(colnames, "month");
        String merchantKey = resolveKey(colnames, "merchant name", "merchant");
        String volumeKey   = resolveKey(colnames, "volume (tl)", "volume");

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String month = row.optString(monthKey, "").trim();
            if (month.isEmpty()) continue;

            String merchant = row.optString(merchantKey, "").trim();
            if (merchant.isEmpty()) continue;

            Double vol = toDoubleSafe(row.opt(volumeKey));
            if (vol == null) continue;

            w23Map.computeIfAbsent(month, k -> new LinkedHashMap<>());

            // Aynı ay + merchant tekrar gelirse üstüne yazmak yerine toplayalım (güvenli taraf)
            w23Map.get(month).merge(merchant, vol, Double::sum);
        }

        // Opsiyonel log
        for (Map.Entry<String, Map<String, Double>> e : w23Map.entrySet()) {
            System.out.println("=== " + e.getKey() + " ===");
            e.getValue().forEach((k, v) -> System.out.println(k + " -> " + v));
        }
    }

    @Then("The user verify scenario13")
    public void theUserVerifyScenario13() {
        boolean scenario13 = areMapsEqual(w22Map,w23Map);

        Assert.assertTrue("s13 fail",scenario13);
    }



    Map<String, Map<String, Double>> w24VolumeMap; // Month -> (Merchant -> Volume)
    Map<String, Map<String, Double>> w24TrxMap;    // Month -> (Merchant -> #OfTrx)

    @Given("The user send widget24 request")
    public void theUserSendW24Request() throws Exception {
        JSONObject v24Json = Requests.sendWidget24Request(); // kendi send metodun
        System.out.println("w24Json: " + v24Json);

        w24VolumeMap = new LinkedHashMap<>(); // gelen sırayı koru
        w24TrxMap    = new LinkedHashMap<>();

        if (v24Json == null) return;
        JSONArray results = v24Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        JSONArray colnames = block0.optJSONArray("colnames");
        JSONArray data     = block0.optJSONArray("data");
        if (colnames == null || data == null) return;

        // Kolon adlarını dinamik çöz
        String monthKey    = resolveKey(colnames, "month");
        String merchantKey = resolveKey(colnames, "merchant name", "merchant");
        String volumeKey   = resolveKey(colnames, "volume (tl)", "volume");
        String trxKey      = resolveKey(colnames, "# of trx", "trx", "trx count");

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String month = row.optString(monthKey, "").trim();
            if (month.isEmpty()) continue;

            String merchant = row.optString(merchantKey, "").trim();
            if (merchant.isEmpty()) continue;

            // Volume
            Double vol = toDoubleSafe(row.opt(volumeKey));
            if (vol != null) {
                w24VolumeMap.computeIfAbsent(month, k -> new LinkedHashMap<>());
                w24VolumeMap.get(month).merge(merchant, vol, Double::sum);
            }

            // # Of Trx
            Double trx = toDoubleSafe(row.opt(trxKey));
            if (trx != null) {
                w24TrxMap.computeIfAbsent(month, k -> new LinkedHashMap<>());
                w24TrxMap.get(month).merge(merchant, trx, Double::sum);
            }
        }

        // Opsiyonel log
        for (Map.Entry<String, Map<String, Double>> e : w24VolumeMap.entrySet()) {
            System.out.println("=== " + e.getKey() + " | Volume (TL) ===");
            e.getValue().forEach((k, v) -> System.out.println(k + " -> " + v));
        }
        for (Map.Entry<String, Map<String, Double>> e : w24TrxMap.entrySet()) {
            System.out.println("=== " + e.getKey() + " | # Of Trx ===");
            e.getValue().forEach((k, v) -> System.out.println(k + " -> " + v));
        }
    }

    @Then("The user verify scenario14")
    public void theUserVerifyScenario14() {
        boolean scenario13 = areMapsEqual(w22Map,w24VolumeMap);

        Assert.assertTrue("s14 fail",scenario13);
    }




    Map<String, Map<String, Double>> w25Map;

    @Given("The user send widget25 request")
    public void theUserSendWidget25Request() throws Exception {
        JSONObject w25Json = Requests.sendWidget25Request(); // kendi request metodun
        System.out.println("w25Json: " + w25Json);

        w25Map = new LinkedHashMap<>(); // eklenme sırasını koru

        if (w25Json == null) return;
        JSONArray results = w25Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        JSONArray colnames = block0.optJSONArray("colnames");
        JSONArray data     = block0.optJSONArray("data");
        if (colnames == null || data == null) return;

        // Kolon adlarını dinamik çöz
        String monthKey    = resolveKey(colnames, "month");
        String merchantKey = resolveKey(colnames, "merchant name", "merchant");
        String volumeKey   = resolveKey(colnames, "volume (tl)", "volume");

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String month = row.optString(monthKey, "").trim();
            if (month.isEmpty()) continue;

            String merchant = row.optString(merchantKey, "").trim();
            if (merchant.isEmpty()) continue;

            Double vol = toDoubleSafe(row.opt(volumeKey));
            if (vol == null) continue;

            w25Map.computeIfAbsent(month, k -> new LinkedHashMap<>());
            // Aynı month + merchant tekrar gelirse toplam al
            w25Map.get(month).merge(merchant, vol, Double::sum);
        }

        // İsteğe bağlı log
        for (Map.Entry<String, Map<String, Double>> e : w25Map.entrySet()) {
            System.out.println("=== " + e.getKey() + " ===");
            e.getValue().forEach((k, v) -> System.out.println(k + " -> " + v));
        }
    }

    @Then("The user verify scenario15")
    public void theUserVerifyScenario15() {
        boolean scenario15 = areMapsEqual(w22Map,w25Map);

        Assert.assertTrue("s15 fail",scenario15);
    }

    double w30TotalNewMerchants;
    @Given("The user send widget30 request")
    public void theUserSendWidget30Request() throws Exception {
        JSONObject w30Json = Requests.sendWidget30Request(); // kendi request metodun
        System.out.println("w30Json: " + w30Json);

        w30TotalNewMerchants = 0.0;

        if (w30Json == null) return;
        JSONArray results = w30Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;
        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        JSONArray colnames = block0.optJSONArray("colnames");
        JSONArray data     = block0.optJSONArray("data");
        if (colnames == null || data == null) return;

        // Kolon anahtarlarını dinamik çöz
        String weekKey   = resolveKey(colnames, "week"); // log veya map yapmak istersen
        String newMerKey = resolveKey(colnames, "new merchants", "live new merchant", "new");

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            // İsteğe bağlı debug:
            // String week = row.optString(weekKey, "");
            // System.out.println("Week: " + week);

            double nm = toDoubleSafe(row.opt(newMerKey));
            w30TotalNewMerchants += nm;
        }

        System.out.println("W30 Total New Merchants = " + w30TotalNewMerchants);
    }

    private static boolean hasKey(JSONArray colnames, String hint) {
        if (colnames == null) return false;
        String h = hint.toLowerCase();
        for (int i = 0; i < colnames.length(); i++) {
            String c = colnames.optString(i, "");
            if (c != null && c.toLowerCase().contains(h)) return true;
        }
        return false;
    }

    double w31NewMerchants;
    @Given("The user send widget31 request")
    public void theUserSendWidget31Request() throws Exception {
        JSONObject w31Json = Requests.sendWidget31Request(); // kendi request metodun
        System.out.println("w31Json: " + w31Json);

        w31NewMerchants = 0.0;

        if (w31Json == null) return;
        JSONArray results = w31Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        boolean foundTotalBlock = false;

        // 1) Önce tek satırlık toplam bloğu bulmayı dene
        for (int b = 0; b < results.length(); b++) {
            JSONObject block = results.optJSONObject(b);
            if (block == null) continue;

            JSONArray colnames = block.optJSONArray("colnames");
            JSONArray data     = block.optJSONArray("data");
            if (colnames == null || data == null || data.length() == 0) continue;

            boolean hasNew    = hasKey(colnames, "new merchants");
            boolean hasWeek   = hasKey(colnames, "week");
            if (hasNew && !hasWeek && data.length() == 1) { // toplam blok
                String newMerKey = resolveKey(colnames, "new merchants");
                JSONObject row0 = data.optJSONObject(0);
                if (row0 != null) {
                    w31NewMerchants = toDoubleSafe(row0.opt(newMerKey));
                    foundTotalBlock = true;
                    break;
                }
            }
        }



        // 2) Bulamazsak haftalık satırlardan toplayalım (fallback)
//        if (!foundTotalBlock) {
//            for (int b = 0; b < results.length(); b++) {
//                JSONObject block = results.optJSONObject(b);
//                if (block == null) continue;
//
//                JSONArray colnames = block.optJSONArray("colnames");
//                JSONArray data     = block.optJSONArray("data");
//                if (colnames == null || data == null) continue;
//
//                if (hasKey(colnames, "week") && hasKey(colnames, "new merchants")) {
//                    String newMerKey = resolveKey(colnames, "new merchants");
//                    for (int i = 0; i < data.length(); i++) {
//                        JSONObject row = data.optJSONObject(i);
//                        if (row == null) continue;
//                        w31NewMerchants += toDoubleSafe(row.opt(newMerKey));
//                    }
//                    break;
//                }
//            }
//        }

        System.out.println("W31 Total New Merchants = " + w31NewMerchants);
    }


    @Then("The user verify scenario16")
    public void theUserVerifyScenario16() {
        Assert.assertEquals("s16 fail",w30TotalNewMerchants,w31NewMerchants,0.01);
    }

    double w32ReferredLeadsTotal;

    @Given("The user send widget32 request")
    public void theUserSendWidget32Request() throws Exception {
        JSONObject w32Json = Requests.sendWidget32Request(); // kendi request metodun
        System.out.println("w32Json: " + w32Json);

        w32ReferredLeadsTotal = 0.0;

        if (w32Json == null) return;
        JSONArray results = w32Json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        // 1) "Week" + "Referred Leads" kolonları olan bloğu bul ve topla
        boolean summed = false;
        for (int b = 0; b < results.length(); b++) {
            JSONObject block = results.optJSONObject(b);
            if (block == null) continue;

            JSONArray colnames = block.optJSONArray("colnames");
            JSONArray data     = block.optJSONArray("data");
            if (colnames == null || data == null) continue;

            if (hasKey(colnames, "week") && hasKey(colnames, "referred leads")) {
                String refKey = resolveKey(colnames, "referred leads");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject row = data.optJSONObject(i);
                    if (row == null) continue;
                    w32ReferredLeadsTotal += toDoubleSafe(row.opt(refKey));
                }
                summed = true;
                break;
            }
        }

        // 2) Fallback: sadece “Referred Leads” kolonu varsa yine topla
        if (!summed) {
            for (int b = 0; b < results.length(); b++) {
                JSONObject block = results.optJSONObject(b);
                if (block == null) continue;

                JSONArray colnames = block.optJSONArray("colnames");
                JSONArray data     = block.optJSONArray("data");
                if (colnames == null || data == null) continue;

                if (hasKey(colnames, "referred leads")) {
                    String refKey = resolveKey(colnames, "referred leads");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject row = data.optJSONObject(i);
                        if (row == null) continue;
                        w32ReferredLeadsTotal += toDoubleSafe(row.opt(refKey));
                    }
                    break;
                }
            }
        }

        System.out.println("W32 Total Referred Leads = " + w32ReferredLeadsTotal);
    }

    double w33ReferredLeadsTotal;

    @Given("The user send widget33 request")
    public void theUserSendWidget33Request() throws Exception {
        JSONObject json = Requests.sendWidget33Request(); // kendi metodun
        System.out.println("w33Json: " + json);

        w33ReferredLeadsTotal = 0.0;

        if (json == null) return;
        JSONArray results = json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        for (int b = 0; b < results.length(); b++) {
            JSONObject block = results.optJSONObject(b);
            if (block == null) continue;

            JSONArray colnames = block.optJSONArray("colnames");
            JSONArray data     = block.optJSONArray("data");
            if (colnames == null || data == null) continue;

            // Kolon adını yakala (case-insensitive ve varyantlara dayanıklı)
            String leadsKey = resolveKey(colnames, "referred leads");
            if (leadsKey.isEmpty()) continue;

            // 1) Eğer tek satırlık özet varsa doğrudan al
            if (data.length() == 1 && data.optJSONObject(0) != null
                    && data.optJSONObject(0).has(leadsKey)) {
                w33ReferredLeadsTotal += toDoubleSafe(data.optJSONObject(0).opt(leadsKey));
                // diğer bloklarda da değer olabilir; break istemiyoruz
                continue;
            }

            // 2) Aksi halde satır satır topla (haftalık kırılım)
//            for (int i = 0; i < data.length(); i++) {
//                JSONObject row = data.optJSONObject(i);
//                if (row == null) continue;
//                if (!row.has(leadsKey)) continue;
//                w33ReferredLeadsTotal += toDoubleSafe(row.opt(leadsKey));
//            }
        }

        System.out.println("W33 Total Referred Leads = " + w33ReferredLeadsTotal);
    }

    @Then("The user verify scenario17")
    public void theUserVerifyScenario17() {
        Assert.assertEquals("s17 fail",w32ReferredLeadsTotal,w33ReferredLeadsTotal,0.01);
    }

    double w34ReferredLeadsTotal;
    Map<String, Double> w34ByPartner;

    @Given("The user send widget34 request")
    public void theUserSendWidget34Request() throws Exception {
        JSONObject json = Requests.sendWidget34Request(); // kendi send metodun
        System.out.println("w34Json: " + json);

        w34ReferredLeadsTotal = 0.0;
        w34ByPartner = new LinkedHashMap<>();

        if (json == null) return;
        JSONArray results = json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        JSONObject block0 = results.optJSONObject(0);
        if (block0 == null) return;

        JSONArray colnames = block0.optJSONArray("colnames");
        JSONArray data     = block0.optJSONArray("data");
        if (colnames == null || data == null) return;

        String partnerKey = resolveKey(colnames, "partner");
        String leadsKey   = resolveKey(colnames, "referred leads");
        if (leadsKey.isEmpty()) return; // kolon bulunamadıysa çık

        for (int i = 0; i < data.length(); i++) {
            JSONObject row = data.optJSONObject(i);
            if (row == null) continue;

            String partner = row.optString(partnerKey, "").trim();
            double leads   = toDoubleSafe(row.opt(leadsKey));

            // toplam
            w34ReferredLeadsTotal += leads;

            // partner kırılımı (opsiyonel)
            if (!partner.isEmpty()) {
                w34ByPartner.merge(partner, leads, Double::sum);
            }
        }

        // Log
        System.out.println("W34 Total Referred Leads = " + w34ReferredLeadsTotal);
        w34ByPartner.forEach((p,v) -> System.out.println(p + " -> " + v));
    }

    double w35ReferredLeads; // partner satırlarının toplamı

    @Given("The user send widget35 request")
    public void theUserSendWidget35Request() throws Exception {
        JSONObject json = Requests.sendWidget35Request();
        System.out.println("w35Json: " + json);

        w35ReferredLeads = 0.0;
        if (json == null) return;

        JSONArray results = json.optJSONArray("result");
        if (results == null || results.length() == 0) return;

        // --- Hedef blok: Partner + Referred Leads kolonları olan ve birden fazla satır dönen blok ---
        JSONObject targetBlock = null;
        int bestRowCount = -1;

        for (int i = 0; i < results.length(); i++) {
            JSONObject block = results.optJSONObject(i);
            if (block == null) continue;

            JSONArray colnames = block.optJSONArray("colnames");
            JSONArray data     = block.optJSONArray("data");
            if (colnames == null || data == null) continue;

            boolean hasReferredLeads = containsIgnoreCase(colnames, "Referred Leads");
            boolean hasPartner       = containsIgnoreCase(colnames, "Partner");
            boolean singleColumn     = colnames.length() == 1;   // alt toplam bloğunu elemek için
            boolean singleRow        = data.length() == 1;

            // Alt toplam bloğunu bilinçli olarak dışla (tek kolon ve tek satır)
            if (hasReferredLeads && singleColumn && singleRow) {
                continue;
            }

            // Öncelik: Partner + Referred Leads + çok satır
            if (hasReferredLeads && hasPartner) {
                int rowCount = data.length();
                if (rowCount > bestRowCount) {
                    bestRowCount = rowCount;
                    targetBlock = block;
                }
            }
        }

        // Eğer Partner+ReferredLeads bulunamadıysa, Referred Leads içeren ve tek kolon/tek satır olmayan herhangi bir bloğu dene
        if (targetBlock == null) {
            for (int i = 0; i < results.length(); i++) {
                JSONObject block = results.optJSONObject(i);
                if (block == null) continue;

                JSONArray colnames = block.optJSONArray("colnames");
                JSONArray data     = block.optJSONArray("data");
                if (colnames == null || data == null) continue;

                boolean hasReferredLeads = containsIgnoreCase(colnames, "Referred Leads");
                boolean singleColumn     = colnames.length() == 1;
                boolean singleRow        = data.length() == 1;

                if (hasReferredLeads && !(singleColumn && singleRow)) {
                    targetBlock = block;
                    break;
                }
            }
        }

        if (targetBlock == null) return;

        JSONArray targetColnames = targetBlock.optJSONArray("colnames");
        JSONArray targetData     = targetBlock.optJSONArray("data");
        if (targetColnames == null || targetData == null || targetData.length() == 0) return;

        String leadsKey = resolveKey(targetColnames, "referred leads");
        if (leadsKey.isEmpty()) return;

        // --- Toplamı partner satırlarından hesapla ---
        double sum = 0.0;
        for (int i = 0; i < targetData.length(); i++) {
            Object rowObj = targetData.opt(i);
            if (rowObj instanceof JSONObject) {
                JSONObject row = (JSONObject) rowObj;
                sum += toDoubleSafe(row.opt(leadsKey));
            } else {
                // Beklenmedik tiplerde (ör. primitive) güvenli atla
            }
        }

        w35ReferredLeads = sum;
        System.out.println("W35 Referred Leads (sum of rows) = " + w35ReferredLeads);
    }

    /** Küçük/büyük harfe duyarsız kolon adı araması */
    private static boolean containsIgnoreCase(JSONArray arr, String key) {
        if (arr == null || key == null) return false;
        for (int i = 0; i < arr.length(); i++) {
            String v = String.valueOf(arr.opt(i));
            if (v != null && v.equalsIgnoreCase(key)) return true;
        }
        return false;
    }



    @Then("The user verify scenario18")
    public void theUserVerifyScenario18() {
        Assert.assertEquals("s18 fail",w34ReferredLeadsTotal,w35ReferredLeads,0.01);
    }






}
