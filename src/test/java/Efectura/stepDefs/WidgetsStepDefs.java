package Efectura.stepDefs;

import Efectura.utilities.Requests;
import Efectura.utilities.databaseMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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


    // W3: Tüm SalesRep'ler için verilen stage sütununu topla
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

    // W3: Pilot ve Live toplamını birlikte hesapla
    public static double[] getPilotAndLiveTotalsFromW3(JSONObject resp) {
        double pilotTotal = sumStageAcrossAllRepsFromW3(resp, "8-Pilot");
        double liveTotal  = sumStageAcrossAllRepsFromW3(resp, "9-Live");
        return new double[]{pilotTotal, liveTotal};
    }


    double totalPilotW3, totalLiveW3;

    @Given("The user send widget3 request")
    public void theUserSendWidget3Request() throws Exception {
        JSONObject w3Json = Requests.sendWidget3Request(); // mevcut send metodun
        System.out.println("w3Json: " + w3Json);

        totalPilotW3 = sumStageAcrossAllRepsFromW3(w3Json, "8-Pilot");
        totalLiveW3  = sumStageAcrossAllRepsFromW3(w3Json, "9-Live");

        System.out.println("Toplam 8-Pilot: " + totalPilotW3);
        System.out.println("Toplam 9-Live : " + totalLiveW3);
    }

    double totalPilotW40, totalLiveW40;

    @Given("The user send widget40 request")
    public void theUserSendWidget40Request() throws IOException {
        // (Varsayım) İstek metodu sende hazır:
        JSONObject w40Json = Requests.sendWidget40Request();
        System.out.println("w40Json: " + w40Json);

        JSONArray dataArray = w40Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotW40 = 0.0;
        totalLiveW40  = 0.0;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);
            totalPilotW40 += obj.optDouble("8-Pilot", 0.0);
            totalLiveW40  += obj.optDouble("9-Live",  0.0);
        }

        System.out.println("Toplam 8-Pilot (W40): " + totalPilotW40);
        System.out.println("Toplam 9-Live  (W40): " + totalLiveW40);
    }

    @Then("The user verify scenario5")
    public void theUserVerifyScenario5() {
        Assert.assertEquals("Senaryo 5 pilot sayılar eşleşmedi",
                totalPilotW3, totalPilotW40, 0.01);

        Assert.assertEquals("Senaryo 5 live sayılar eşleşmedi",
                totalLiveW3, totalLiveW40, 0.01);
    }

    double totalPilotW4, totalLiveW4;

    @Given("The user send widget4 request")
    public void theUserSendWidget4Request() throws IOException {
        // JSON'ı çek (senin mevcut request metodunla)
        JSONObject w4Json = Requests.sendWidget4Request();
        System.out.println("w4Json: " + w4Json);

        if (w4Json == null) {
            totalPilotW4 = 0;
            totalLiveW4 = 0;
            return;
        }

        JSONArray dataArray = w4Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotW4 = 0.0;
        totalLiveW4 = 0.0;

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
        }

        System.out.println("W4 Pilot toplam: " + totalPilotW4);
        System.out.println("W4 Live toplam: " + totalLiveW4);
    }

    double totalPilotW41;
    double totalLiveW41;

    @Given("The user send widget41 request")
    public void theUserSendWidget41Request() throws IOException {
        JSONObject w41Json = Requests.sendWidget41Request(); // varsa; yoksa kendi request'ini yap
        JSONArray data = w41Json
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("data");

        totalPilotW41 = 0;
        totalLiveW41 = 0;

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

            // Hem isimle (Pilot/Live) hem de 8-Pilot/9-Live etiketleriyle eşleştiriyoruz:
            if ("Pilot".equalsIgnoreCase(stage) || "8-Pilot".equalsIgnoreCase(stage)) {
                totalPilotW41 += count;
            } else if ("Live".equalsIgnoreCase(stage) || "9-Live".equalsIgnoreCase(stage)) {
                totalLiveW41 += count;
            }
        }

        System.out.println("Pilot toplam Customer Count (W41): " + (long) totalPilotW41);
        System.out.println("Live toplam Customer Count (W41): " + (long) totalLiveW41);
    }

    @Then("The user verify scenario6")
    public void theUserVerifyScenario6() {
        Assert.assertEquals("Senaryo 6 pilot sayılar eşleşmedi",
                totalPilotW4, totalPilotW41, 0.01);

        Assert.assertEquals("Senaryo 6 live sayılar eşleşmedi",
                totalLiveW4, totalLiveW41, 0.01);
    }
}
