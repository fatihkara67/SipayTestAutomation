package Efectura.utilities;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Requests {

    static int timeoutValue = Integer.parseInt(ConfigurationReader.getProperty("timeout"));

    public static JSONObject sendWidget36Request() {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A433%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // crm-dashboard için session cookie’ni burada tutuyorsun

        // Curl’deki body ile birebir
        String body = """
    {"datasource":{"id":36,"type":"table"},"force":true,"queries":[{"filters":[{"col":"Team","op":"IN","val":["Sales"]}],"extras":{"having":"","where":"(StageDate >= today()) AND (SalesStage NOT IN ('Live', 'Growth')) AND (SalesStage IS NOT NULL)"},"applied_time_extras":{},"columns":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"metrics":[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":"{\\"warning_markdown\\":null}","filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count","optionName":"metric_8l94erlcc36_d1kvs1t4eza","sqlExpression":null}],"annotation_layers":[],"row_limit":100,"series_limit":0,"order_desc":true,"url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"433"},"custom_params":{},"custom_form_data":{}}],"form_data":{"datasource":"36__table","viz_type":"pie","slice_id":433,"url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"433"},"groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"metric":{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":"{\\"warning_markdown\\":null}","filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count","optionName":"metric_8l94erlcc36_d1kvs1t4eza","sqlExpression":null},"adhoc_filters":[{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_pe8t08a1hlo_d64xn8mjfl","isExtra":false,"isNew":false,"operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE","sqlExpression":"StageDate >= today()","subject":"CreatedOn"},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_fpuq6o0l7i9_ad9jgnjxhs","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage NOT IN ('Live', 'Growth')","subject":null},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_ng9a3kuyjli_km3hsu4md6","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage IS NOT NULL","subject":null},{"clause":"WHERE","comparator":["Sales"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_cu5j6wvmg7i_odixc71myc","isExtra":false,"isNew":false,"operator":"IN","operatorId":"IN","sqlExpression":null,"subject":"Team"}],"row_limit":100,"sort_by_metric":false,"color_scheme":"supersetColors","show_labels_threshold":5,"show_legend":true,"legendType":"scroll","legendOrientation":"left","label_type":"value_percent","number_format":"SMART_NUMBER","date_format":"smart_date","show_labels":true,"labels_outside":true,"label_line":true,"show_total":false,"outerRadius":72,"donut":true,"innerRadius":46,"extra_form_data":{},"force":true,"result_format":"json","result_type":"full"},"result_format":"json","result_type":"full"}
    """;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept-Language", "en-US,en;q=0.9,tr;q=0.8")
                .addHeader("Origin", "https://crm-dashboard.spwgpf.com")
                .addHeader("Referer", "https://crm-dashboard.spwgpf.com/explore/?slice_id=433")
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Cookie", cookie)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .build();

        try (Response resp = client.newCall(request).execute()) {
            if (!resp.isSuccessful()) {
                String err = resp.body() != null ? resp.body().string() : "";
                throw new IOException("Unexpected code " + resp.code() + " - " + err);
            }
            String respStr = resp.body() != null ? resp.body().string() : "{}";
            return new JSONObject(respStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject sendWidget37Request() {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A434%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        // Jenkins/local fark etmeksizin dinamik cookie
        String cookie = ConfigurationReader.getProperty("cookie");

        // cURL'deki --data-raw gövdesi (aynen)
        String body = """
    {"datasource":{"id":36,"type":"table"},"force":true,"queries":[{"filters":[{"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]},{"col":"SalesStageAggr","op":"IN","val":["3-Pitched"]},{"col":"Team","op":"IN","val":["Sales"]}],"extras":{"having":"","where":"(StageDate >= today()) AND (SalesStage IS NOT NULL) AND (OfferStatus IS NOT NULL)"},"applied_time_extras":{},"columns":[{"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"}],"metrics":[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":"{\\"warning_markdown\\":null}","filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":false,"label":"COUNT_DISTINCT(AssetId)","optionName":"metric_cvrevass8p_o7afn6pef1m","sqlExpression":null}],"orderby":[[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":"{\\"warning_markdown\\":null}","filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":false,"label":"COUNT_DISTINCT(AssetId)","optionName":"metric_cvrevass8p_o7afn6pef1m","sqlExpression":null},false]],"annotation_layers":[],"row_limit":50000,"series_columns":[],"series_limit":0,"order_desc":true,"url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"434"},"custom_params":{},"custom_form_data":{},"time_offsets":[],"post_processing":[{"operation":"pivot","options":{"index":["SalesRep"],"columns":[],"aggregates":{"COUNT_DISTINCT(AssetId)":{"operator":"mean"}},"drop_missing_columns":false}},{"operation":"flatten"}]}],"form_data":{"datasource":"36__table","viz_type":"echarts_timeseries_bar","slice_id":434,"url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"434"},"x_axis":"SalesRep","x_axis_sort_asc":true,"x_axis_sort_series":"name","x_axis_sort_series_ascending":true,"metrics":[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":"{\\"warning_markdown\\":null}","filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":false,"label":"COUNT_DISTINCT(AssetId)","optionName":"metric_cvrevass8p_o7afn6pef1m","sqlExpression":null}],"groupby":[],"adhoc_filters":[{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_pe8t08a1hlo_d64xn8mjfl","isExtra":false,"isNew":false,"operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE","sqlExpression":"StageDate >= today()","subject":"CreatedOn"},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_ng9a3kuyjli_km3hsu4md6","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage IS NOT NULL","subject":null},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_042w8gdjkegd_ggw5fhcm7cr","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"OfferStatus IS NOT NULL","subject":null},{"clause":"WHERE","comparator":["Test Sipay"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_5w7zq1c3bbm_asdz59ie79","isExtra":false,"isNew":false,"operator":"NOT IN","operatorId":"NOT_IN","sqlExpression":null,"subject":"SalesRep"},{"clause":"WHERE","comparator":["3-Pitched"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_yx3okzsga0d_684hmbry6gh","isExtra":false,"isNew":false,"operator":"IN","operatorId":"IN","sqlExpression":null,"subject":"SalesStageAggr"},{"clause":"WHERE","comparator":["Sales"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_esejw9tgklk_f4p0gtvvnss","isExtra":false,"isNew":false,"operator":"IN","operatorId":"IN","sqlExpression":null,"subject":"Team"}],"order_desc":true,"row_limit":50000,"truncate_metric":true,"show_empty_columns":true,"comparison_type":"values","annotation_layers":[],"forecastPeriods":10,"forecastInterval":0.8,"orientation":"vertical","x_axis_title_margin":15,"y_axis_title_margin":15,"y_axis_title_position":"Left","sort_series_type":"name","sort_series_ascending":true,"color_scheme":"supersetColors","show_value":true,"stack":"Stack","only_total":true,"show_legend":false,"legendType":"scroll","legendOrientation":"top","x_axis_time_format":"smart_date","xAxisLabelRotation":90,"y_axis_format":"SMART_NUMBER","logAxis":false,"truncateXAxis":true,"y_axis_bounds":[null,null],"rich_tooltip":true,"showTooltipTotal":true,"showTooltipPercentage":true,"tooltipTimeFormat":"smart_date","extra_form_data":{},"force":true,"result_format":"json","result_type":"full"},"result_format":"json","result_type":"full"}
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                // Minimum gerekenler bunlar. İstersen aşağıdakileri de ekleyebilirsin:
                .addHeader("Origin", "https://crm-dashboard.spwgpf.com")
                .addHeader("Referer", "https://crm-dashboard.spwgpf.com/explore/?slice_id=434")
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Cookie", cookie) // <— cookie’yi config’ten al
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String err = response.body() != null ? response.body().string() : "";
                throw new IOException("Widget37 HTTP " + response.code() + " - " + err);
            }
            String resp = response.body().string();
            return new JSONObject(resp);
        } catch (Exception e) {
            throw new RuntimeException("sendWidget37Request() çağrısı başarısız oldu", e);
        }
    }

    public static JSONObject sendWidget38Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A431%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        // Cookie'yi config'ten çekiyoruz (Jenkins/local fark etmez)
        String cookie = ConfigurationReader.getProperty("cookie");

        // cURL'deki --data-raw gövdesi (aynen)
        String body = """
    {"datasource":{"id":36,"type":"table"},"force":true,"queries":[{"filters":[{"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]},{"col":"Team","op":"IN","val":["Sales"]}],"extras":{"having":"","where":"(StageDate >= today()) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"},"applied_time_extras":{},"columns":[{"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"},{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"metrics":[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":null,"filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count","optionName":"metric_w67h0a7x6fk_2zllxkz1jgr","sqlExpression":null}],"orderby":[[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":null,"filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count","optionName":"metric_w67h0a7x6fk_2zllxkz1jgr","sqlExpression":null},false]],"annotation_layers":[],"row_limit":50000,"series_columns":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"series_limit":0,"order_desc":true,"url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"431"},"custom_params":{},"custom_form_data":{},"time_offsets":[],"post_processing":[{"operation":"pivot","options":{"index":["SalesRep"],"columns":["Sales Stage"],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},{"operation":"rename","options":{"columns":{"Customer Count":null},"level":0,"inplace":true}},{"operation":"flatten"}]}],"form_data":{"datasource":"36__table","viz_type":"echarts_timeseries_bar","slice_id":431,"url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"431"},"x_axis":"SalesRep","x_axis_sort_asc":true,"x_axis_sort_series":"name","x_axis_sort_series_ascending":false,"metrics":[{"aggregate":"COUNT_DISTINCT","column":{"advanced_data_type":null,"changed_on":"2025-08-12T16:11:02.352726","column_name":"AssetId","created_on":"2025-08-12T16:11:02.352724","description":null,"expression":null,"extra":null,"filterable":true,"groupby":true,"id":384,"is_active":true,"is_dttm":false,"python_date_format":null,"type":"INT","type_generic":0,"uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc","verbose_name":null},"datasourceWarning":false,"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count","optionName":"metric_w67h0a7x6fk_2zllxkz1jgr","sqlExpression":null}],"groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"adhoc_filters":[{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_pe8t08a1hlo_d64xn8mjfl","isExtra":false,"isNew":false,"operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE","sqlExpression":"StageDate >= today()","subject":"CreatedOn"},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_ng9a3kuyjli_km3hsu4md6","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage IS NOT NULL","subject":null},{"clause":"WHERE","comparator":["Test Sipay"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_9vk4xkqre4n_348iwu9ew0k","isExtra":false,"isNew":false,"operator":"NOT IN","operatorId":"NOT_IN","sqlExpression":null,"subject":"SalesRep"},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_2rf8s86pvtb_6i4kzz22za6","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')","subject":null},{"clause":"WHERE","comparator":null,"datasourceWarning":false,"expressionType":"SQL","filterOptionName":"filter_pzqawsydi7f_5b3fkzu2uw","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot')","subject":null},{"clause":"WHERE","comparator":["Sales"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_j1munj64juc_y22ouz1td6","isExtra":false,"isNew":false,"operator":"IN","operatorId":"IN","sqlExpression":null,"subject":"Team"}],"order_desc":true,"row_limit":50000,"truncate_metric":true,"show_empty_columns":true,"comparison_type":"values","annotation_layers":[],"forecastPeriods":10,"forecastInterval":0.8,"orientation":"horizontal","x_axis_title_margin":15,"y_axis_title_margin":15,"y_axis_title_position":"Left","sort_series_type":"name","sort_series_ascending":true,"color_scheme":"supersetColors","show_value":true,"stack":"Stack","only_total":true,"show_legend":true,"legendType":"scroll","legendOrientation":"bottom","x_axis_time_format":"smart_date","xAxisLabelRotation":0,"y_axis_format":"SMART_NUMBER","logAxis":false,"truncateXAxis":true,"y_axis_bounds":[null,null],"rich_tooltip":true,"showTooltipTotal":true,"showTooltipPercentage":true,"tooltipTimeFormat":"smart_date","extra_form_data":{},"force":true,"result_format":"json","result_type":"full"},"result_format":"json","result_type":"full"}
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Origin", "https://crm-dashboard.spwgpf.com")
                .addHeader("Referer", "https://crm-dashboard.spwgpf.com/explore/?slice_id=431")
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String err = response.body() != null ? response.body().string() : "";
                throw new IOException("Widget38 HTTP " + response.code() + " - " + err);
            }
            String resp = response.body().string();
            return new JSONObject(resp);
        } catch (Exception e) {
            throw new RuntimeException("sendWidget38Request() çağrısı başarısız oldu", e);
        }
    }

    public static JSONObject sendWidget39Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A432%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        // Cookie (Jenkins/local fark etmeksizin dinamik)
        String cookie = ConfigurationReader.getProperty("cookie");

        // cURL'deki --data-raw içeriği (shell kaçışlarını temizledim)
        String body = """
    {
      "datasource": { "id": 36, "type": "table" },
      "force": true,
      "queries": [
        {
          "filters": [
            { "col": "Team", "op": "IN", "val": ["Sales"] }
          ],
          "extras": {
            "having": "",
            "where": "(StageDate >= today()) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "expressionType": "SQL",
              "label": "Sales Stage",
              "sqlExpression": "CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
            },
            "SalesRep"
          ],
          "metrics": [
            {
              "aggregate": "COUNT_DISTINCT",
              "column": {
                "advanced_data_type": null,
                "changed_on": "2025-08-12T16:11:02.352726",
                "column_name": "AssetId",
                "created_on": "2025-08-12T16:11:02.352724",
                "description": null,
                "expression": null,
                "extra": "{}",
                "filterable": true,
                "groupby": true,
                "id": 384,
                "is_active": true,
                "is_dttm": false,
                "python_date_format": null,
                "type": "INT",
                "type_generic": 0,
                "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
                "verbose_name": null
              },
              "datasourceWarning": false,
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Customer Count",
              "optionName": "metric_txudnh06lt_3rx0o3p3x14",
              "sqlExpression": null
            }
          ],
          "orderby": [
            [
              {
                "aggregate": "COUNT_DISTINCT",
                "column": {
                  "advanced_data_type": null,
                  "changed_on": "2025-08-12T16:11:02.352726",
                  "column_name": "AssetId",
                  "created_on": "2025-08-12T16:11:02.352724",
                  "description": null,
                  "expression": null,
                  "extra": "{}",
                  "filterable": true,
                  "groupby": true,
                  "id": 384,
                  "is_active": true,
                  "is_dttm": false,
                  "python_date_format": null,
                  "type": "INT",
                  "type_generic": 0,
                  "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
                  "verbose_name": null
                },
                "datasourceWarning": false,
                "expressionType": "SIMPLE",
                "hasCustomLabel": true,
                "label": "Customer Count",
                "optionName": "metric_txudnh06lt_3rx0o3p3x14",
                "sqlExpression": null
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 50000,
          "series_limit": 0,
          "order_desc": true,
          "url_params": { "dashboard_page_id": "VowOmSI7wGn2RuNBz_6QF", "slice_id": "432" },
          "custom_params": {},
          "custom_form_data": {}
        }
      ],
      "form_data": {
        "datasource": "36__table",
        "viz_type": "pivot_table_v2",
        "slice_id": 432,
        "url_params": { "dashboard_page_id": "VowOmSI7wGn2RuNBz_6QF", "slice_id": "432" },
        "groupbyColumns": [
          {
            "expressionType": "SQL",
            "label": "Sales Stage",
            "sqlExpression": "CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
          }
        ],
        "groupbyRows": ["SalesRep"],
        "temporal_columns_lookup": { "StageDate": true, "EndDate": true },
        "metrics": [
          {
            "aggregate": "COUNT_DISTINCT",
            "column": {
              "advanced_data_type": null,
              "changed_on": "2025-08-12T16:11:02.352726",
              "column_name": "AssetId",
              "created_on": "2025-08-12T16:11:02.352724",
              "description": null,
              "expression": null,
              "extra": "{}",
              "filterable": true,
              "groupby": true,
              "id": 384,
              "is_active": true,
              "is_dttm": false,
              "python_date_format": null,
              "type": "INT",
              "type_generic": 0,
              "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
              "verbose_name": null
            },
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count",
            "optionName": "metric_txudnh06lt_3rx0o3p3x14",
            "sqlExpression": null
          }
        ],
        "metricsLayout": "COLUMNS",
        "adhoc_filters": [
          {
            "clause": "WHERE",
            "comparator": null,
            "datasourceWarning": false,
            "expressionType": "SQL",
            "filterOptionName": "filter_pe8t08a1hlo_d64xn8mjfl",
            "isExtra": false,
            "isNew": false,
            "operator": "TEMPORAL_RANGE",
            "operatorId": "TEMPORAL_RANGE",
            "sqlExpression": "StageDate >= today()",
            "subject": "CreatedOn"
          },
          { "clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_ng9a3kuyjli_km3hsu4md6", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStage IS NOT NULL", "subject": null },
          { "clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_quih0rmyzr_ayqmeb0jt3e", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')", "subject": null },
          { "clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_zivup6c7tqr_z3qc02xrf3e", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot')", "subject": null },
          { "clause": "WHERE", "comparator": ["Sales"], "datasourceWarning": false, "expressionType": "SIMPLE", "filterOptionName": "filter_qfe2wvqudui_da4uze2acck", "isExtra": false, "isNew": false, "operator": "IN", "operatorId": "IN", "sqlExpression": null, "subject": "Team" }
        ],
        "row_limit": 50000,
        "order_desc": true,
        "aggregateFunction": "Sum",
        "colTotals": true,
        "valueFormat": "SMART_NUMBER",
        "date_format": "smart_date",
        "rowOrder": "key_a_to_z",
        "colOrder": "key_a_to_z",
        "extra_form_data": {},
        "force": true,
        "result_format": "json",
        "result_type": "full"
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Origin", "https://crm-dashboard.spwgpf.com")
                .addHeader("Referer", "https://crm-dashboard.spwgpf.com/explore/?slice_id=432")
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String err = response.body() != null ? response.body().string() : "";
                throw new IOException("Widget39 HTTP " + response.code() + " - " + err);
            }
            String resp = response.body().string();
            return new JSONObject(resp);
        } catch (Exception e) {
            throw new RuntimeException("sendWidget39Request() çağrısı başarısız oldu", e);
        }
    }

    public static JSONObject sendWidget3Request() {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A222%7D";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(7, TimeUnit.SECONDS)
                .readTimeout(7, TimeUnit.SECONDS)
                .writeTimeout(7, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
            {
              "datasource": {
                "id": 36,
                "type": "table"
              },
              "force": false,
              "queries": [
                {
                  "filters": [
                    {
                      "col": "SalesRep",
                      "op": "NOT IN",
                      "val": [
                        "Test Sipay"
                      ]
                    }
                  ],
                  "extras": {
                    "having": "",
                    "where": "(StageDate >= toStartOfWeek(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT ILIKE '%RET%')"
                  },
                  "applied_time_extras": {},
                  "columns": [
                    {
                      "columnType": "BASE_AXIS",
                      "sqlExpression": "SalesRep",
                      "label": "SalesRep",
                      "expressionType": "SQL"
                    },
                    {
                      "expressionType": "SQL",
                      "label": "Sales Stage",
                      "sqlExpression": "StageAggr"
                    }
                  ],
                  "metrics": [
                    {
                      "aggregate": "COUNT_DISTINCT",
                      "column": {
                        "advanced_data_type": null,
                        "changed_on": "2025-08-12T16:11:02.352726",
                        "column_name": "AssetId",
                        "created_on": "2025-08-12T16:11:02.352724",
                        "description": null,
                        "expression": null,
                        "extra": null,
                        "filterable": true,
                        "groupby": true,
                        "id": 384,
                        "is_active": true,
                        "is_dttm": false,
                        "python_date_format": null,
                        "type": "INT",
                        "type_generic": 0,
                        "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
                        "verbose_name": null
                      },
                      "datasourceWarning": false,
                      "expressionType": "SIMPLE",
                      "hasCustomLabel": true,
                      "label": "Customer Count",
                      "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
                      "sqlExpression": null
                    }
                  ],
                  "orderby": [
                    [
                      {
                        "aggregate": "COUNT_DISTINCT",
                        "column": {
                          "advanced_data_type": null,
                          "changed_on": "2025-08-12T16:11:02.352726",
                          "column_name": "AssetId",
                          "created_on": "2025-08-12T16:11:02.352724",
                          "description": null,
                          "expression": null,
                          "extra": null,
                          "filterable": true,
                          "groupby": true,
                          "id": 384,
                          "is_active": true,
                          "is_dttm": false,
                          "python_date_format": null,
                          "type": "INT",
                          "type_generic": 0,
                          "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
                          "verbose_name": null
                        },
                        "datasourceWarning": false,
                        "expressionType": "SIMPLE",
                        "hasCustomLabel": true,
                        "label": "Customer Count",
                        "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
                        "sqlExpression": null
                      },
                      false
                    ]
                  ],
                  "annotation_layers": [],
                  "row_limit": 50000,
                  "series_columns": [
                    {
                      "expressionType": "SQL",
                      "label": "Sales Stage",
                      "sqlExpression": "StageAggr"
                    }
                  ],
                  "series_limit": 0,
                  "order_desc": true,
                  "url_params": {
                    "dashboard_page_id": "MgZI7Yvj6TzEPtC9kExvN",
                    "form_data_key": "pTlipsLFEqO4LK8wDiBRyqZL4jV9zHWWh7RWSZO68Tcd8SArjHiDI6mAaobfPeN4",
                    "slice_id": "222"
                  },
                  "custom_params": {},
                  "custom_form_data": {},
                  "time_offsets": [],
                  "post_processing": [
                    {
                      "operation": "pivot",
                      "options": {
                        "index": [
                          "SalesRep"
                        ],
                        "columns": [
                          "Sales Stage"
                        ],
                        "aggregates": {
                          "Customer Count": {
                            "operator": "mean"
                          }
                        },
                        "drop_missing_columns": false
                      }
                    },
                    {
                      "operation": "rename",
                      "options": {
                        "columns": {
                          "Customer Count": null
                        },
                        "level": 0,
                        "inplace": true
                      }
                    },
                    {
                      "operation": "flatten"
                    }
                  ]
                }
              ],
              "form_data": {
                "datasource": "36__table",
                "viz_type": "echarts_timeseries_bar",
                "slice_id": 222,
                "url_params": {
                  "dashboard_page_id": "MgZI7Yvj6TzEPtC9kExvN",
                  "form_data_key": "pTlipsLFEqO4LK8wDiBRyqZL4jV9zHWWh7RWSZO68Tcd8SArjHiDI6mAaobfPeN4",
                  "slice_id": "222"
                },
                "x_axis": "SalesRep",
                "x_axis_sort_series": "name",
                "x_axis_sort_series_ascending": false,
                "metrics": [
                  {
                    "aggregate": "COUNT_DISTINCT",
                    "column": {
                      "advanced_data_type": null,
                      "changed_on": "2025-08-12T16:11:02.352726",
                      "column_name": "AssetId",
                      "created_on": "2025-08-12T16:11:02.352724",
                      "description": null,
                      "expression": null,
                      "extra": null,
                      "filterable": true,
                      "groupby": true,
                      "id": 384,
                      "is_active": true,
                      "is_dttm": false,
                      "python_date_format": null,
                      "type": "INT",
                      "type_generic": 0,
                      "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
                      "verbose_name": null
                    },
                    "datasourceWarning": false,
                    "expressionType": "SIMPLE",
                    "hasCustomLabel": true,
                    "label": "Customer Count",
                    "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
                    "sqlExpression": null
                  }
                ],
                "groupby": [
                  {
                    "expressionType": "SQL",
                    "label": "Sales Stage",
                    "sqlExpression": "StageAggr"
                  }
                ],
                "adhoc_filters": [
                  {
                    "expressionType": "SQL",
                    "sqlExpression": "StageDate >= toStartOfWeek(today())",
                    "clause": "WHERE",
                    "subject": "CreatedOn",
                    "operator": "TEMPORAL_RANGE",
                    "operatorId": "TEMPORAL_RANGE",
                    "comparator": null,
                    "isExtra": false,
                    "isNew": false,
                    "datasourceWarning": false,
                    "filterOptionName": "filter_pe8t08a1hlo_d64xn8mjfl"
                  },
                  {
                    "expressionType": "SQL",
                    "sqlExpression": "SalesStage IS NOT NULL",
                    "clause": "WHERE",
                    "subject": null,
                    "operator": null,
                    "comparator": null,
                    "isExtra": false,
                    "isNew": false,
                    "datasourceWarning": false,
                    "filterOptionName": "filter_ng9a3kuyjli_km3hsu4md6"
                  },
                  {
                    "expressionType": "SIMPLE",
                    "subject": "SalesRep",
                    "operator": "NOT IN",
                    "operatorId": "NOT_IN",
                    "comparator": [
                      "Test Sipay"
                    ],
                    "clause": "WHERE",
                    "sqlExpression": null,
                    "isExtra": false,
                    "isNew": false,
                    "datasourceWarning": false,
                    "filterOptionName": "filter_9vk4xkqre4n_348iwu9ew0k"
                  },
                  {
                    "expressionType": "SQL",
                    "sqlExpression": "SalesStage NOT ILIKE '%RET%'",
                    "clause": "WHERE",
                    "subject": null,
                    "operator": null,
                    "comparator": null,
                    "isExtra": false,
                    "isNew": false,
                    "datasourceWarning": false,
                    "filterOptionName": "filter_hvagzuof8e9_movljtu97i"
                  }
                ],
                "row_limit": 50000,
                "truncate_metric": true,
                "show_empty_columns": true,
                "annotation_layers": [],
                "orientation": "horizontal",
                "x_axis_title_margin": 15,
                "y_axis_title_margin": 15,
                "y_axis_title_position": "Left",
                "sort_series_type": "name",
                "sort_series_ascending": true,
                "color_scheme": "supersetColors",
                "show_value": true,
                "stack": "Stack",
                "only_total": true,
                "show_legend": true,
                "legendType": "scroll",
                "legendOrientation": "bottom",
                "x_axis_time_format": "smart_date",
                "y_axis_format": "SMART_NUMBER",
                "logAxis": false,
                "truncateXAxis": true,
                "y_axis_bounds": [
                  null,
                  null
                ],
                "rich_tooltip": true,
                "showTooltipTotal": true,
                "showTooltipPercentage": true,
                "tooltipTimeFormat": "smart_date",
                "extra_form_data": {},
                "force": false,
                "result_format": "json",
                "result_type": "full"
              },
              "result_format": "json",
              "result_type": "full"
            }
            """;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept-Language", "en-US,en;q=0.9,tr;q=0.8")
                .addHeader("Origin", "https://crm-dashboard.spwgpf.com")
                .addHeader("Referer", "https://crm-dashboard.spwgpf.com/explore/?form_data_key=pTlipsLFEqO4LK8wDiBRyqZL4jV9zHWWh7RWSZO68Tcd8SArjHiDI6mAaobfPeN4&dashboard_page_id=MgZI7Yvj6TzEPtC9kExvN&slice_id=222")
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Cookie", cookie)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .build();

        try (Response resp = client.newCall(request).execute()) {
            if (!resp.isSuccessful()) {
                String err = resp.body() != null ? resp.body().string() : "";
                throw new IOException("Unexpected code " + resp.code() + " - " + err);
            }
            String respStr = resp.body() != null ? resp.body().string() : "{}";
            return new JSONObject(respStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static JSONObject sendWidget40Request() {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A436%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(6, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // -> properties’te tut

        RequestBody body = RequestBody.create(
                """
                {"datasource":{"id":36,"type":"table"},"force":true,"queries":[{"filters":[{"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]},{"col":"Team","op":"IN","val":["Sales"]}],"extras":{"having":"","where":"(StageDate >= toStartOfWeek(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"},"applied_time_extras":{},"columns":[{"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"},{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"metrics":[{"aggregate":"COUNT_DISTINCT","column":{"column_name":"AssetId","id":384,"type":"INT"},"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count"}],"orderby":[[{"aggregate":"COUNT_DISTINCT","column":{"column_name":"AssetId","id":384,"type":"INT"},"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count"},false]],"row_limit":50000,"series_columns":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"series_limit":0,"order_desc":true,"url_params":{"slice_id":"436"},"post_processing":[{"operation":"pivot","options":{"index":["SalesRep"],"columns":["Sales Stage"],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},{"operation":"rename","options":{"columns":{"Customer Count":null},"level":0,"inplace":true}},{"operation":"flatten"}]}],"form_data":{"datasource":"36__table","viz_type":"echarts_timeseries_bar","slice_id":436,"groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],"x_axis":"SalesRep","metrics":[{"aggregate":"COUNT_DISTINCT","column":{"column_name":"AssetId","id":384,"type":"INT"},"expressionType":"SIMPLE","hasCustomLabel":true,"label":"Customer Count"}],"adhoc_filters":[{"clause":"WHERE","operator":"TEMPORAL_RANGE","sqlExpression":"StageDate >= toStartOfWeek(today())","subject":"CreatedOn"},{"clause":"WHERE","sqlExpression":"SalesStage IS NOT NULL"},{"clause":"WHERE","operator":"NOT_IN","subject":"SalesRep","comparator":["Test Sipay"]},{"clause":"WHERE","sqlExpression":"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')"},{"clause":"WHERE","sqlExpression":"SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot')"},{"clause":"WHERE","operator":"IN","subject":"Team","comparator":["Sales"]}],"result_format":"json","result_type":"full"},"result_format":"json","result_type":"full"}
                """,
                okhttp3.MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respBody = response.body().string();
            return new JSONObject(respBody);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject sendWidget4Request() throws IOException {
        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // gerekiyorsa ayrı crmCookie kullan
        String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A183%7D&force=true";

        String bodyStr = "{\n" +
                "  \"datasource\": { \"id\": 36, \"type\": \"table\" },\n" +
                "  \"force\": true,\n" +
                "  \"queries\": [\n" +
                "    {\n" +
                "      \"filters\": [],\n" +
                "      \"extras\": {\n" +
                "        \"having\": \"\",\n" +
                "        \"where\": \"(StageDate >= toStartOfWeek(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet'))\"\n" +
                "      },\n" +
                "      \"applied_time_extras\": {},\n" +
                "      \"columns\": [\n" +
                "        {\n" +
                "          \"expressionType\": \"SQL\",\n" +
                "          \"label\": \"Sales Stage\",\n" +
                "          \"sqlExpression\": \"CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END\"\n" +
                "        },\n" +
                "        \"SalesRep\"\n" +
                "      ],\n" +
                "      \"metrics\": [\n" +
                "        {\n" +
                "          \"aggregate\": \"COUNT_DISTINCT\",\n" +
                "          \"column\": { \"column_name\": \"AssetId\" },\n" +
                "          \"expressionType\": \"SIMPLE\",\n" +
                "          \"hasCustomLabel\": true,\n" +
                "          \"label\": \"Customer Count\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"orderby\": [[ { \"aggregate\": \"COUNT_DISTINCT\", \"column\": { \"column_name\": \"AssetId\" }, \"expressionType\": \"SIMPLE\", \"label\": \"Customer Count\" }, false]],\n" +
                "      \"annotation_layers\": [],\n" +
                "      \"row_limit\": 50000,\n" +
                "      \"series_limit\": 0,\n" +
                "      \"order_desc\": true,\n" +
                "      \"url_params\": { \"slice_id\": \"183\" },\n" +
                "      \"custom_params\": {},\n" +
                "      \"custom_form_data\": {}\n" +
                "    }\n" +
                "  ],\n" +
                "  \"form_data\": {\n" +
                "    \"datasource\": \"36__table\",\n" +
                "    \"viz_type\": \"pivot_table_v2\",\n" +
                "    \"slice_id\": 183,\n" +
                "    \"groupbyColumns\": [\n" +
                "      {\n" +
                "        \"expressionType\": \"SQL\",\n" +
                "        \"label\": \"Sales Stage\",\n" +
                "        \"sqlExpression\": \"CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"groupbyRows\": [\"SalesRep\"],\n" +
                "    \"metrics\": [ { \"aggregate\": \"COUNT_DISTINCT\", \"column\": { \"column_name\": \"AssetId\" }, \"expressionType\": \"SIMPLE\", \"label\": \"Customer Count\" } ],\n" +
                "    \"adhoc_filters\": [\n" +
                "      { \"clause\": \"WHERE\", \"expressionType\": \"SQL\", \"sqlExpression\": \"StageDate >= toStartOfWeek(today())\" },\n" +
                "      { \"clause\": \"WHERE\", \"expressionType\": \"SQL\", \"sqlExpression\": \"SalesStage IS NOT NULL\" },\n" +
                "      { \"clause\": \"WHERE\", \"expressionType\": \"SQL\", \"sqlExpression\": \"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')\" }\n" +
                "    ],\n" +
                "    \"aggregateFunction\": \"Sum\",\n" +
                "    \"colTotals\": true,\n" +
                "    \"result_format\": \"json\",\n" +
                "    \"result_type\": \"full\"\n" +
                "  },\n" +
                "  \"result_format\": \"json\",\n" +
                "  \"result_type\": \"full\"\n" +
                "}";

        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "session=" + cookie) // domain’in beklediği cookie formatına göre ayarla
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Widget4 request failed: " + response.code() + " - " + response.message());
            }
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget41Request() throws IOException {
        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");
        String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A437%7D&force=true";

        String bodyStr = """
    {
      "datasource": { "id": 36, "type": "table" },
      "force": true,
      "queries": [
        {
          "filters": [
            { "col": "Team", "op": "IN", "val": ["Sales"] }
          ],
          "extras": {
            "having": "",
            "where": "(StageDate >= toStartOfWeek(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "expressionType": "SQL",
              "label": "Sales Stage",
              "sqlExpression": "CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
            },
            "SalesRep"
          ],
          "metrics": [
            {
              "aggregate": "COUNT_DISTINCT",
              "column": { "column_name": "AssetId" },
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Customer Count"
            }
          ],
          "orderby": [
            [
              {
                "aggregate": "COUNT_DISTINCT",
                "column": { "column_name": "AssetId" },
                "expressionType": "SIMPLE",
                "label": "Customer Count"
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 50000,
          "series_limit": 0,
          "order_desc": true,
          "url_params": { "slice_id": "437" },
          "custom_params": {},
          "custom_form_data": {}
        }
      ],
      "form_data": {
        "datasource": "36__table",
        "viz_type": "pivot_table_v2",
        "slice_id": 437,
        "groupbyColumns": [
          {
            "expressionType": "SQL",
            "label": "Sales Stage",
            "sqlExpression": "CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
          }
        ],
        "groupbyRows": ["SalesRep"],
        "metrics": [
          {
            "aggregate": "COUNT_DISTINCT",
            "column": { "column_name": "AssetId" },
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count"
          }
        ],
        "adhoc_filters": [
          { "clause": "WHERE", "expressionType": "SQL", "sqlExpression": "StageDate >= toStartOfWeek(today())" },
          { "clause": "WHERE", "expressionType": "SQL", "sqlExpression": "SalesStage IS NOT NULL" },
          { "clause": "WHERE", "expressionType": "SQL", "sqlExpression": "SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')" },
          { "clause": "WHERE", "expressionType": "SQL", "sqlExpression": "SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot')" },
          { "clause": "WHERE", "expressionType": "SIMPLE", "subject": "Team", "operator": "IN", "comparator": ["Sales"] }
        ],
        "row_limit": 50000,
        "order_desc": true,
        "aggregateFunction": "Sum",
        "colTotals": true,
        "result_format": "json",
        "result_type": "full"
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "session=" + cookie)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Widget41 request failed: " + response.code() + " - " + response.message());
            }
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget42Request() {
        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // CRM cookie

        String bodyStr = """
{
  "datasource": {"id": 36, "type": "table"},
  "force": true,
  "queries": [
    {
      "filters": [
        {"col": "SalesRep", "op": "NOT IN", "val": ["Test Sipay"]},
        {"col": "Team", "op": "IN", "val": ["Sales"]}
      ],
      "extras": {
        "having": "",
        "where": "(StageDate >= toStartOfMonth(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"
      },
      "applied_time_extras": {},
      "columns": [
        {"columnType": "BASE_AXIS", "sqlExpression": "SalesRep", "label": "SalesRep", "expressionType": "SQL"},
        {"expressionType": "SQL", "label": "Sales Stage", "sqlExpression": "StageAggr"}
      ],
      "metrics": [
        {
          "aggregate": "COUNT_DISTINCT",
          "column": {
            "advanced_data_type": null,
            "changed_on": "2025-08-12T16:11:02.352726",
            "column_name": "AssetId",
            "created_on": "2025-08-12T16:11:02.352724",
            "description": null,
            "expression": null,
            "extra": null,
            "filterable": true,
            "groupby": true,
            "id": 384,
            "is_active": true,
            "is_dttm": false,
            "python_date_format": null,
            "type": "INT",
            "type_generic": 0,
            "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
            "verbose_name": null
          },
          "datasourceWarning": false,
          "expressionType": "SIMPLE",
          "hasCustomLabel": true,
          "label": "Customer Count",
          "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
          "sqlExpression": null
        }
      ],
      "orderby": [
        [
          {
            "aggregate": "COUNT_DISTINCT",
            "column": {
              "advanced_data_type": null,
              "changed_on": "2025-08-12T16:11:02.352726",
              "column_name": "AssetId",
              "created_on": "2025-08-12T16:11:02.352724",
              "description": null,
              "expression": null,
              "extra": null,
              "filterable": true,
              "groupby": true,
              "id": 384,
              "is_active": true,
              "is_dttm": false,
              "python_date_format": null,
              "type": "INT",
              "type_generic": 0,
              "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
              "verbose_name": null
            },
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count",
            "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
            "sqlExpression": null
          },
          false
        ]
      ],
      "annotation_layers": [],
      "row_limit": 50000,
      "series_columns": [{"expressionType": "SQL", "label": "Sales Stage", "sqlExpression": "StageAggr"}],
      "series_limit": 0,
      "order_desc": true,
      "url_params": {"dashboard_page_id": "VowOmSI7wGn2RuNBz_6QF", "slice_id": "429"},
      "custom_params": {},
      "custom_form_data": {},
      "time_offsets": [],
      "post_processing": [
        {
          "operation": "pivot",
          "options": {
            "index": ["SalesRep"],
            "columns": ["Sales Stage"],
            "aggregates": {"Customer Count": {"operator": "mean"}},
            "drop_missing_columns": false
          }
        },
        {"operation": "rename", "options": {"columns": {"Customer Count": null}, "level": 0, "inplace": true}},
        {"operation": "flatten"}
      ]
    }
  ],
  "form_data": {
    "datasource": "36__table",
    "viz_type": "echarts_timeseries_bar",
    "slice_id": 429,
    "url_params": {"dashboard_page_id": "VowOmSI7wGn2RuNBz_6QF", "slice_id": "429"},
    "x_axis": "SalesRep",
    "x_axis_sort_asc": true,
    "x_axis_sort_series": "name",
    "x_axis_sort_series_ascending": false,
    "metrics": [
      {
        "aggregate": "COUNT_DISTINCT",
        "column": {
          "advanced_data_type": null,
          "changed_on": "2025-08-12T16:11:02.352726",
          "column_name": "AssetId",
          "created_on": "2025-08-12T16:11:02.352724",
          "description": null,
          "expression": null,
          "extra": null,
          "filterable": true,
          "groupby": true,
          "id": 384,
          "is_active": true,
          "is_dttm": false,
          "python_date_format": null,
          "type": "INT",
          "type_generic": 0,
          "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
          "verbose_name": null
        },
        "datasourceWarning": false,
        "expressionType": "SIMPLE",
        "hasCustomLabel": true,
        "label": "Customer Count",
        "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
        "sqlExpression": null
      }
    ],
    "groupby": [{"expressionType": "SQL", "label": "Sales Stage", "sqlExpression": "StageAggr"}],
    "adhoc_filters": [
      {"clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_pe8t08a1hlo_d64xn8mjfl", "isExtra": false, "isNew": false, "operator": "TEMPORAL_RANGE", "operatorId": "TEMPORAL_RANGE", "sqlExpression": "StageDate >= toStartOfMonth(today())", "subject": "CreatedOn"},
      {"clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_ng9a3kuyjli_km3hsu4md6", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStage IS NOT NULL", "subject": null},
      {"clause": "WHERE", "comparator": ["Test Sipay"], "datasourceWarning": false, "expressionType": "SIMPLE", "filterOptionName": "filter_9vk4xkqre4n_348iwu9ew0k", "isExtra": false, "isNew": false, "operator": "NOT IN", "operatorId": "NOT_IN", "sqlExpression": null, "subject": "SalesRep"},
      {"clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_2izuttf1xeh_ey6n0kzte8o", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')", "subject": null},
      {"clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_0qt37m8fk8ql_rliskdyzrb", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot')", "subject": null},
      {"clause": "WHERE", "comparator": ["Sales"], "datasourceWarning": false, "expressionType": "SIMPLE", "filterOptionName": "filter_g0qsu0yuo3c_6y13kd2miev", "isExtra": false, "isNew": false, "operator": "IN", "operatorId": "IN", "sqlExpression": null, "subject": "Team"}
    ],
    "order_desc": true,
    "row_limit": 50000,
    "truncate_metric": true,
    "show_empty_columns": true,
    "comparison_type": "values",
    "annotation_layers": [],
    "forecastPeriods": 10,
    "forecastInterval": 0.8,
    "orientation": "horizontal",
    "x_axis_title_margin": 15,
    "y_axis_title_margin": 15,
    "y_axis_title_position": "Left",
    "sort_series_type": "name",
    "sort_series_ascending": true,
    "color_scheme": "supersetColors",
    "show_value": true,
    "stack": "Stack",
    "only_total": true,
    "show_legend": true,
    "legendType": "scroll",
    "legendOrientation": "bottom",
    "x_axis_time_format": "smart_date",
    "xAxisLabelRotation": 0,
    "y_axis_format": "SMART_NUMBER",
    "logAxis": false,
    "truncateXAxis": true,
    "y_axis_bounds": [null, null],
    "rich_tooltip": true,
    "showTooltipTotal": true,
    "showTooltipPercentage": true,
    "tooltipTimeFormat": "smart_date",
    "extra_form_data": {},
    "force": true,
    "result_format": "json",
    "result_type": "full"
  },
  "result_format": "json",
  "result_type": "full"
}
""";

        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A429%7D&force=true")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject sendWidget3SpecialS7Request() {
        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String bodyStr = """
{
  "datasource": { "id": 36, "type": "table" },
  "force": true,
  "queries": [
    {
      "filters": [
        { "col": "SalesRep", "op": "NOT IN", "val": ["Test Sipay"] }
      ],
      "extras": {
        "having": "",
        "where": "(StageDate >= toStartOfMonth(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet'))"
      },
      "applied_time_extras": {},
      "columns": [
        { "columnType": "BASE_AXIS", "sqlExpression": "SalesRep", "label": "SalesRep", "expressionType": "SQL" },
        { "expressionType": "SQL", "label": "Sales Stage", "sqlExpression": "StageAggr" }
      ],
      "metrics": [
        {
          "aggregate": "COUNT_DISTINCT",
          "column": {
            "advanced_data_type": null,
            "changed_on": "2025-08-12T16:11:02.352726",
            "column_name": "AssetId",
            "created_on": "2025-08-12T16:11:02.352724",
            "description": null,
            "expression": null,
            "extra": null,
            "filterable": true,
            "groupby": true,
            "id": 384,
            "is_active": true,
            "is_dttm": false,
            "python_date_format": null,
            "type": "INT",
            "type_generic": 0,
            "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
            "verbose_name": null
          },
          "datasourceWarning": false,
          "expressionType": "SIMPLE",
          "hasCustomLabel": true,
          "label": "Customer Count",
          "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
          "sqlExpression": null
        }
      ],
      "orderby": [
        [
          {
            "aggregate": "COUNT_DISTINCT",
            "column": {
              "advanced_data_type": null,
              "changed_on": "2025-08-12T16:11:02.352726",
              "column_name": "AssetId",
              "created_on": "2025-08-12T16:11:02.352724",
              "description": null,
              "expression": null,
              "extra": null,
              "filterable": true,
              "groupby": true,
              "id": 384,
              "is_active": true,
              "is_dttm": false,
              "python_date_format": null,
              "type": "INT",
              "type_generic": 0,
              "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
              "verbose_name": null
            },
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count",
            "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
            "sqlExpression": null
          },
          false
        ]
      ],
      "annotation_layers": [],
      "row_limit": 50000,
      "series_columns": [
        { "expressionType": "SQL", "label": "Sales Stage", "sqlExpression": "StageAggr" }
      ],
      "series_limit": 0,
      "order_desc": true,
      "url_params": { "dashboard_page_id": "bhzW-j_0JOtVR6bKLFQ8p", "slice_id": "236" },
      "custom_params": {},
      "custom_form_data": {},
      "time_offsets": [],
      "post_processing": [
        {
          "operation": "pivot",
          "options": {
            "index": ["SalesRep"],
            "columns": ["Sales Stage"],
            "aggregates": { "Customer Count": { "operator": "mean" } },
            "drop_missing_columns": false
          }
        },
        { "operation": "rename", "options": { "columns": { "Customer Count": null }, "level": 0, "inplace": true } },
        { "operation": "flatten" }
      ]
    }
  ],
  "form_data": {
    "datasource": "36__table",
    "viz_type": "echarts_timeseries_bar",
    "slice_id": 236,
    "url_params": { "dashboard_page_id": "bhzW-j_0JOtVR6bKLFQ8p", "slice_id": "236" },
    "x_axis": "SalesRep",
    "x_axis_sort_asc": true,
    "x_axis_sort_series": "name",
    "x_axis_sort_series_ascending": false,
    "metrics": [
      {
        "aggregate": "COUNT_DISTINCT",
        "column": {
          "advanced_data_type": null,
          "changed_on": "2025-08-12T16:11:02.352726",
          "column_name": "AssetId",
          "created_on": "2025-08-12T16:11:02.352724",
          "description": null,
          "expression": null,
          "extra": null,
          "filterable": true,
          "groupby": true,
          "id": 384,
          "is_active": true,
          "is_dttm": false,
          "python_date_format": null,
          "type": "INT",
          "type_generic": 0,
          "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
          "verbose_name": null
        },
        "datasourceWarning": false,
        "expressionType": "SIMPLE",
        "hasCustomLabel": true,
        "label": "Customer Count",
        "optionName": "metric_w67h0a7x6fk_2zllxkz1jgr",
        "sqlExpression": null
      }
    ],
    "groupby": [
      { "expressionType": "SQL", "label": "Sales Stage", "sqlExpression": "StageAggr" }
    ],
    "adhoc_filters": [
      { "clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_pe8t08a1hlo_d64xn8mjfl", "isExtra": false, "isNew": false, "operator": "TEMPORAL_RANGE", "operatorId": "TEMPORAL_RANGE", "sqlExpression": "StageDate >= toStartOfMonth(today())", "subject": "CreatedOn" },
      { "clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_ng9a3kuyjli_km3hsu4md6", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStage IS NOT NULL", "subject": null },
      { "clause": "WHERE", "comparator": ["Test Sipay"], "datasourceWarning": false, "expressionType": "SIMPLE", "filterOptionName": "filter_9vk4xkqre4n_348iwu9ew0k", "isExtra": false, "isNew": false, "operator": "NOT IN", "operatorId": "NOT_IN", "sqlExpression": null, "subject": "SalesRep" },
      { "clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_2izuttf1xeh_ey6n0kzte8o", "isExtra": false, "isNew": false, "operator": null, "sqlExpression": "SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')", "subject": null }
    ],
    "order_desc": true,
    "row_limit": 50000,
    "truncate_metric": true,
    "show_empty_columns": true,
    "comparison_type": "values",
    "annotation_layers": [],
    "forecastPeriods": 10,
    "forecastInterval": 0.8,
    "orientation": "horizontal",
    "x_axis_title_margin": 15,
    "y_axis_title_margin": 15,
    "y_axis_title_position": "Left",
    "sort_series_type": "name",
    "sort_series_ascending": true,
    "color_scheme": "supersetColors",
    "show_value": true,
    "stack": "Stack",
    "only_total": true,
    "show_legend": true,
    "legendType": "scroll",
    "legendOrientation": "bottom",
    "x_axis_time_format": "smart_date",
    "xAxisLabelRotation": 0,
    "y_axis_format": "SMART_NUMBER",
    "logAxis": false,
    "truncateXAxis": true,
    "y_axis_bounds": [null, null],
    "rich_tooltip": true,
    "showTooltipTotal": true,
    "showTooltipPercentage": true,
    "tooltipTimeFormat": "smart_date",
    "extra_form_data": {},
    "force": true,
    "result_format": "json",
    "result_type": "full"
  },
  "result_format": "json",
  "result_type": "full"
}
""";

        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A236%7D&force=true")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject sendWidget43Request() {
        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(7, TimeUnit.SECONDS)
                .readTimeout(7, TimeUnit.SECONDS)
                .writeTimeout(7, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // CRM dashboard cookie

        // cURL'deki body - parametresiz (Team=Sales, StageDate >= toStartOfMonth(today()), 7-Implementation/9-Live/8-Pilot)
        String bodyStr = """
{
  "datasource": {"id":36,"type":"table"},
  "force": true,
  "queries": [
    {
      "filters": [
        {"col":"Team","op":"IN","val":["Sales"]}
      ],
      "extras": {
        "having": "",
        "where": "(StageDate >= toStartOfMonth(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"
      },
      "applied_time_extras": {},
      "columns": [
        {
          "expressionType":"SQL",
          "label":"Sales Stage",
          "sqlExpression":"CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
        },
        "SalesRep"
      ],
      "metrics": [
        {
          "aggregate":"COUNT_DISTINCT",
          "column":{
            "advanced_data_type":null,
            "changed_on":"2025-08-12T16:11:02.352726",
            "column_name":"AssetId",
            "created_on":"2025-08-12T16:11:02.352724",
            "description":null,
            "expression":null,
            "extra":"{}",
            "filterable":true,
            "groupby":true,
            "id":384,
            "is_active":true,
            "is_dttm":false,
            "python_date_format":null,
            "type":"INT",
            "type_generic":0,
            "uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc",
            "verbose_name":null
          },
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count",
          "optionName":"metric_txudnh06lt_3rx0o3p3x14",
          "sqlExpression":null
        }
      ],
      "orderby": [
        [
          {
            "aggregate":"COUNT_DISTINCT",
            "column":{
              "advanced_data_type":null,
              "changed_on":"2025-08-12T16:11:02.352726",
              "column_name":"AssetId",
              "created_on":"2025-08-12T16:11:02.352724",
              "description":null,
              "expression":null,
              "extra":"{}",
              "filterable":true,
              "groupby":true,
              "id":384,
              "is_active":true,
              "is_dttm":false,
              "python_date_format":null,
              "type":"INT",
              "type_generic":0,
              "uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc",
              "verbose_name":null
            },
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count",
            "optionName":"metric_txudnh06lt_3rx0o3p3x14",
            "sqlExpression":null
          },
          false
        ]
      ],
      "annotation_layers": [],
      "row_limit": 50000,
      "series_limit": 0,
      "order_desc": true,
      "url_params": {
        "dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF",
        "slice_id":"430"
      },
      "custom_params": {},
      "custom_form_data": {}
    }
  ],
  "form_data": {
    "datasource":"36__table",
    "viz_type":"pivot_table_v2",
    "slice_id":430,
    "url_params":{"dashboard_page_id":"VowOmSI7wGn2RuNBz_6QF","slice_id":"430"},
    "groupbyColumns":[
      {
        "expressionType":"SQL",
        "label":"Sales Stage",
        "sqlExpression":"CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
      }
    ],
    "groupbyRows":["SalesRep"],
    "temporal_columns_lookup":{"StageDate":true,"EndDate":true},
    "metrics":[
      {
        "aggregate":"COUNT_DISTINCT",
        "column":{
          "advanced_data_type":null,
          "changed_on":"2025-08-12T16:11:02.352726",
          "column_name":"AssetId",
          "created_on":"2025-08-12T16:11:02.352724",
          "description":null,
          "expression":null,
          "extra":"{}",
          "filterable":true,
          "groupby":true,
          "id":384,
          "is_active":true,
          "is_dttm":false,
          "python_date_format":null,
          "type":"INT",
          "type_generic":0,
          "uuid":"5a95266a-8162-4daa-9419-0e378a6aafdc",
          "verbose_name":null
        },
        "datasourceWarning":false,
        "expressionType":"SIMPLE",
        "hasCustomLabel":true,
        "label":"Customer Count",
        "optionName":"metric_txudnh06lt_3rx0o3p3x14",
        "sqlExpression":null
      }
    ],
    "metricsLayout":"COLUMNS",
    "adhoc_filters":[
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_pe8t08a1hlo_d64xn8mjfl","isExtra":false,"isNew":false,"operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE","sqlExpression":"StageDate >= toStartOfMonth(today())","subject":"CreatedOn","comparator":null,"datasourceWarning":false},
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_ng9a3kuyjli_km3hsu4md6","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage IS NOT NULL","subject":null,"comparator":null,"datasourceWarning":false},
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_3sw3jzn67xe_ii2kn9qorlg","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')","subject":null,"comparator":null,"datasourceWarning":false},
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_u0hfhvnv5y_mwq49ek1lg","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStageAggr IN ('7-Implementation', '9-Live', '8-Pilot')","subject":null,"comparator":null,"datasourceWarning":false},
      {"clause":"WHERE","comparator":["Sales"],"datasourceWarning":false,"expressionType":"SIMPLE","filterOptionName":"filter_7ja7f5wl06x_dove6cwqern","isExtra":false,"isNew":false,"operator":"IN","operatorId":"IN","sqlExpression":null,"subject":"Team"}
    ],
    "row_limit":50000,
    "order_desc":true,
    "aggregateFunction":"Sum",
    "colTotals":true,
    "valueFormat":"SMART_NUMBER",
    "date_format":"smart_date",
    "rowOrder":"key_a_to_z",
    "colOrder":"key_a_to_z",
    "extra_form_data":{},
    "force":true,
    "result_format":"json",
    "result_type":"full"
  },
  "result_format":"json",
  "result_type":"full"
}
""";

        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A430%7D&force=true")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String resp = response.body().string();
            return new JSONObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject sendWidget4SpecialS8Request() {
        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        // cURL'deki body - parametresiz
        String bodyStr = """
{
  "datasource": {"id":36,"type":"table"},
  "force": true,
  "queries": [
    {
      "filters": [],
      "extras": {
        "having": "",
        "where": "(StageDate >= toStartOfMonth(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet'))"
      },
      "applied_time_extras": {},
      "columns": [
        {
          "expressionType": "SQL",
          "label": "Sales Stage",
          "sqlExpression": "CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
        },
        "SalesRep"
      ],
      "metrics": [
        {
          "aggregate": "COUNT_DISTINCT",
          "column": {
            "advanced_data_type": null,
            "changed_on": "2025-08-12T16:11:02.352726",
            "column_name": "AssetId",
            "created_on": "2025-08-12T16:11:02.352724",
            "description": null,
            "expression": null,
            "extra": "{}",
            "filterable": true,
            "groupby": true,
            "id": 384,
            "is_active": true,
            "is_dttm": false,
            "python_date_format": null,
            "type": "INT",
            "type_generic": 0,
            "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
            "verbose_name": null
          },
          "datasourceWarning": false,
          "expressionType": "SIMPLE",
          "hasCustomLabel": true,
          "label": "Customer Count",
          "optionName": "metric_txudnh06lt_3rx0o3p3x14",
          "sqlExpression": null
        }
      ],
      "orderby": [
        [
          {
            "aggregate": "COUNT_DISTINCT",
            "column": {
              "advanced_data_type": null,
              "changed_on": "2025-08-12T16:11:02.352726",
              "column_name": "AssetId",
              "created_on": "2025-08-12T16:11:02.352724",
              "description": null,
              "expression": null,
              "extra": "{}",
              "filterable": true,
              "groupby": true,
              "id": 384,
              "is_active": true,
              "is_dttm": false,
              "python_date_format": null,
              "type": "INT",
              "type_generic": 0,
              "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
              "verbose_name": null
            },
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count",
            "optionName": "metric_txudnh06lt_3rx0o3p3x14",
            "sqlExpression": null
          },
          false
        ]
      ],
      "annotation_layers": [],
      "row_limit": 50000,
      "series_limit": 0,
      "order_desc": true,
      "url_params": {
        "dashboard_page_id": "bhzW-j_0JOtVR6bKLFQ8p",
        "slice_id": "237"
      },
      "custom_params": {},
      "custom_form_data": {}
    }
  ],
  "form_data": {
    "datasource": "36__table",
    "viz_type": "pivot_table_v2",
    "slice_id": 237,
    "url_params": {"dashboard_page_id":"bhzW-j_0JOtVR6bKLFQ8p","slice_id":"237"},
    "groupbyColumns": [
      {
        "expressionType": "SQL",
        "label": "Sales Stage",
        "sqlExpression": "CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
      }
    ],
    "groupbyRows": ["SalesRep"],
    "temporal_columns_lookup": {"StageDate": true, "EndDate": true},
    "metrics": [
      {
        "aggregate": "COUNT_DISTINCT",
        "column": {
          "advanced_data_type": null,
          "changed_on": "2025-08-12T16:11:02.352726",
          "column_name": "AssetId",
          "created_on": "2025-08-12T16:11:02.352724",
          "description": null,
          "expression": null,
          "extra": "{}",
          "filterable": true,
          "groupby": true,
          "id": 384,
          "is_active": true,
          "is_dttm": false,
          "python_date_format": null,
          "type": "INT",
          "type_generic": 0,
          "uuid": "5a95266a-8162-4daa-9419-0e378a6aafdc",
          "verbose_name": null
        },
        "datasourceWarning": false,
        "expressionType": "SIMPLE",
        "hasCustomLabel": true,
        "label": "Customer Count",
        "optionName": "metric_txudnh06lt_3rx0o3p3x14",
        "sqlExpression": null
      }
    ],
    "metricsLayout": "COLUMNS",
    "adhoc_filters": [
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_pe8t08a1hlo_d64xn8mjfl","isExtra":false,"isNew":false,"operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE","sqlExpression":"StageDate >= toStartOfMonth(today())","subject":"CreatedOn","comparator":null,"datasourceWarning":false},
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_ng9a3kuyjli_km3hsu4md6","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage IS NOT NULL","subject":null,"comparator":null,"datasourceWarning":false},
      {"clause":"WHERE","expressionType":"SQL","filterOptionName":"filter_3sw3jzn67xe_ii2kn9qorlg","isExtra":false,"isNew":false,"operator":null,"sqlExpression":"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')","subject":null,"comparator":null,"datasourceWarning":false}
    ],
    "row_limit": 50000,
    "order_desc": true,
    "aggregateFunction": "Sum",
    "colTotals": true,
    "valueFormat": "SMART_NUMBER",
    "date_format": "smart_date",
    "rowOrder": "key_a_to_z",
    "colOrder": "key_a_to_z",
    "extra_form_data": {},
    "force": true,
    "result_format": "json",
    "result_type": "full"
  },
  "result_format": "json",
  "result_type": "full"
}
""";

        RequestBody body = RequestBody.create(bodyStr, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A237%7D&force=true")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject sendWidget1Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A180%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": { "id": 36, "type": "table" },
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "having": "",
            "where": "(StageDate >= toStartOfWeek(today())) AND (SalesStage NOT IN ('Live', 'Growth')) AND (SalesStage IS NOT NULL) AND (SalesRep IS NOT NULL)"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "expressionType": "SQL",
              "label": "Sales Stage",
              "sqlExpression": "StageAggr"
            }
          ],
          "metrics": [
            {
              "aggregate": "COUNT_DISTINCT",
              "column": {
                "column_name": "AssetId",
                "id": 384,
                "type": "INT"
              },
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Customer Count"
            }
          ],
          "annotation_layers": [],
          "row_limit": 100,
          "series_limit": 0,
          "order_desc": true,
          "orderby": [
            [
              {
                "aggregate": "COUNT_DISTINCT",
                "column": {
                  "column_name": "AssetId",
                  "id": 384,
                  "type": "INT"
                },
                "expressionType": "SIMPLE",
                "label": "Customer Count"
              },
              false
            ]
          ],
          "url_params": {
            "slice_id": "180"
          },
          "custom_params": {},
          "custom_form_data": {}
        }
      ],
      "form_data": {
        "datasource": "36__table",
        "viz_type": "pie",
        "slice_id": 180,
        "groupby": [
          {
            "expressionType": "SQL",
            "label": "Sales Stage",
            "sqlExpression": "StageAggr"
          }
        ],
        "metric": {
          "aggregate": "COUNT_DISTINCT",
          "column": {
            "column_name": "AssetId",
            "id": 384,
            "type": "INT"
          },
          "expressionType": "SIMPLE",
          "hasCustomLabel": true,
          "label": "Customer Count"
        },
        "adhoc_filters": [
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "StageDate >= toStartOfWeek(today())",
            "subject": "CreatedOn",
            "operator": "TEMPORAL_RANGE",
            "operatorId": "TEMPORAL_RANGE"
          },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "SalesStage NOT IN ('Live', 'Growth')"
          },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "SalesStage IS NOT NULL"
          },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "SalesRep IS NOT NULL"
          }
        ],
        "row_limit": 100
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }


    public static JSONObject sendWidget5Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A184%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": {"id":36,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [
            {"col":"EstimatedMonthlyVolumeTRY","op":"IS NOT NULL"},
            {"col":"EstimatedMonthlyVolumeTRY","op":"NOT IN","val":[0]},
            {"col":"SalesStage","op":"NOT IN","val":["Prospect","ProspectDraft","ContractRet","PitchedRet","LeadRet","Ret","ProspectRet","Live"]}
          ],
          "extras": {
            "having": "",
            "where": "(StageDate >= toStartOfWeek(today())) AND (SalesStage NOT IN ('Live')) AND (SalesStage NOT IN ('ProspectRet', 'Prospect', 'ProspectDraft'))"
          },
          "applied_time_extras": {},
          "columns": [
            {"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"},
            {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"BeklenenAylikTRY","id":294},
              "expressionType":"SIMPLE",
              "hasCustomLabel": true,
              "label":"Estimated Yearly Volume (TRY)"
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"BeklenenAylikTRY","id":294},
                "expressionType":"SIMPLE",
                "hasCustomLabel": true,
                "label":"Estimated Yearly Volume (TRY)"
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 10000,
          "series_columns": [
            {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "series_limit": 0,
          "order_desc": true,
          "url_params": {"slice_id":"184"},
          "custom_params": {},
          "custom_form_data": {},
          "time_offsets": [],
          "post_processing":[
            {
              "operation":"pivot",
              "options":{
                "index":["SalesRep"],
                "columns":["Sales Stage"],
                "aggregates":{"Estimated Yearly Volume (TRY)":{"operator":"mean"}},
                "drop_missing_columns":false
              }
            },
            {"operation":"rename","options":{"columns":{"Estimated Yearly Volume (TRY)":null},"level":0,"inplace":true}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data": {
        "datasource":"36__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":184,
        "x_axis":"SalesRep",
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":false,
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"BeklenenAylikTRY","id":294},
            "expressionType":"SIMPLE",
            "hasCustomLabel": true,
            "label":"Estimated Yearly Volume (TRY)"
          }
        ],
        "groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageDate >= toStartOfWeek(today())","subject":"CreatedOn","operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('Live')"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"EstimatedMonthlyVolumeTRY","operator":"IS NOT NULL"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"EstimatedMonthlyVolumeTRY","operator":"NOT_IN","comparator":[0]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('ProspectRet', 'Prospect', 'ProspectDraft')"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"SalesStage","operator":"NOT_IN","comparator":["Prospect","ProspectDraft","ContractRet","PitchedRet","LeadRet","Ret","ProspectRet","Live"]}
        ],
        "row_limit":10000,
        "orientation":"horizontal",
        "stack":"Stack",
        "only_total":true,
        "show_value":true,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"bottom",
        "y_axis_format":"SMART_NUMBER",
        "truncate_metric":true,
        "show_empty_columns":true
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget6Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A185%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": {"id":36,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [
            {"col":"SalesStage","op":"NOT IN","val":["ContractRet","PitchedRet","LeadRet","Ret","ProspectRet","Live"]}
          ],
          "extras": {
            "having":"",
            "where":"(StageDate >= toStartOfWeek(today())) AND (SalesStage NOT IN ('Live', 'Growth')) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ProspectRet', 'Prospect', 'ProspectDraft'))"
          },
          "applied_time_extras":{},
          "columns":[
            {
              "expressionType":"SQL",
              "label":"Sales Stage",
              "sqlExpression":"CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
            },
            "SalesRep"
          ],
          "metrics":[
            {
              "aggregate":"SUM",
              "column":{"column_name":"BeklenenAylikTRY","id":368},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count"
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"BeklenenAylikTRY","id":368},
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Customer Count"
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"185"},
          "custom_params":{},
          "custom_form_data":{}
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"pivot_table_v2",
        "slice_id":185,
        "groupbyColumns":[
          {
            "expressionType":"SQL",
            "label":"Sales Stage",
            "sqlExpression":"CASE WHEN SalesStage LIKE '%Pro%' THEN 'Prospect'\\nWHEN SalesStage LIKE '%Lead%' THEN 'Lead'\\nWHEN SalesStage LIKE '%Contract%' THEN 'Contract'\\nWHEN SalesStage LIKE '%Pitch%' THEN 'Pitched'\\nWHEN SalesStage LIKE '%Onboarding%' THEN 'Onboarding'\\nWHEN SalesStage LIKE '%Ret%' THEN 'Reject'\\nELSE SalesStage END"
          }
        ],
        "groupbyRows":["SalesRep"],
        "temporal_columns_lookup":{"StageDate":true,"EndDate":true},
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"BeklenenAylikTRY","id":368},
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count"
          }
        ],
        "metricsLayout":"COLUMNS",
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageDate >= toStartOfWeek(today())","subject":"CreatedOn","operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('Live', 'Growth')"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage IS NOT NULL"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('ProspectRet', 'Prospect', 'ProspectDraft')"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"SalesStage","operator":"NOT_IN","comparator":["ContractRet","PitchedRet","LeadRet","Ret","ProspectRet","Live"]}
        ],
        "row_limit":50000,
        "order_desc":true,
        "aggregateFunction":"Sum",
        "colTotals":true,
        "valueFormat":"SMART_NUMBER",
        "date_format":"smart_date",
        "rowOrder":"key_a_to_z",
        "colOrder":"key_a_to_z",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget16Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A419%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config’den al

        String body = """
    {
      "datasource": {"id":38,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "time_grain_sqla":"P1D",
            "having":"",
            "where":"(week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today()))"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "timeGrain":"P1D",
              "columnType":"BASE_AXIS",
              "expressionType":"SQL",
              "label":"Week",
              "sqlExpression":"week_label"
            }
          ],
          "metrics": [
            {
              "expressionType":"SQL",
              "hasCustomLabel":true,
              "label":"POS Volume (TL)",
              "sqlExpression":"SUM(POSVolume)"
            },
            {
              "expressionType":"SQL",
              "label":"MAX(replaceAll(toString(week_start), '-', ''))",
              "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
            }
          ],
          "orderby": [
            [
              {
                "expressionType":"SQL",
                "label":"MAX(replaceAll(toString(week_start), '-', ''))",
                "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 1000,
          "series_columns": [],
          "series_limit": 0,
          "series_limit_metric": {
            "expressionType":"SQL",
            "label":"MAX(replaceAll(toString(week_start), '-', ''))",
            "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
          },
          "order_desc": true,
          "url_params": {"slice_id":"419"},
          "custom_params": {},
          "custom_form_data": {},
          "time_offsets": [],
          "post_processing": [
            {
              "operation":"pivot",
              "options":{
                "index":["Week"],
                "columns":[],
                "aggregates":{
                  "POS Volume (TL)":{"operator":"mean"},
                  "MAX(replaceAll(toString(week_start), '-', ''))":{"operator":"mean"}
                },
                "drop_missing_columns": false
              }
            },
            {
              "operation":"sort",
              "options":{"by":"MAX(replaceAll(toString(week_start), '-', ''))","ascending": true}
            },
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data": {
        "datasource":"38__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":419,
        "x_axis":{"expressionType":"SQL","label":"Week","sqlExpression":"week_label"},
        "time_grain_sqla":"P1D",
        "x_axis_sort":"MAX(replaceAll(toString(week_start), '-', ''))",
        "x_axis_sort_asc": true,
        "metrics": [
          {
            "expressionType":"SQL",
            "hasCustomLabel":true,
            "label":"POS Volume (TL)",
            "sqlExpression":"SUM(POSVolume)"
          }
        ],
        "groupby": [],
        "adhoc_filters": [
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"week_start",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today())"
          }
        ],
        "timeseries_limit_metric": {
          "expressionType":"SQL",
          "label":"MAX(replaceAll(toString(week_start), '-', ''))",
          "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
        },
        "order_desc": true,
        "row_limit": 1000,
        "truncate_metric": true,
        "show_empty_columns": true,
        "comparison_type":"values",
        "annotation_layers": [],
        "forecastPeriods": 10,
        "forecastInterval": 0.8,
        "orientation":"vertical",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"sum",
        "color_scheme":"supersetColors",
        "show_value": true,
        "only_total": true,
        "show_legend": true,
        "legendType":"scroll",
        "legendOrientation":"top",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":45,
        "y_axis_format":"SMART_NUMBER",
        "logAxis": false,
        "truncateXAxis": true,
        "truncateYAxis": false,
        "y_axis_bounds":[1, null],
        "rich_tooltip": true,
        "showTooltipTotal": true,
        "showTooltipPercentage": true,
        "tooltipTimeFormat":"smart_date",
        "extra_form_data": {},
        "force": true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget17Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A412%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": {"id":38,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "having":"",
            "where":"(week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today()))"
          },
          "applied_time_extras": {},
          "columns": [
            { "expressionType":"SQL", "label":"Week", "sqlExpression":"week_label" }
          ],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"POSVolume","id":461,"type":"Nullable(Float64)"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"POS Volume (TL)"
            },
            {
              "aggregate":"SUM",
              "column":{"column_name":"LiveNewMerchant","id":462,"type":"UInt64"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Live (New Merchant)"
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"MAX",
                "column":{"column_name":"week_start","id":458,"is_dttm":true,"type":"Nullable(Date)"},
                "expressionType":"SIMPLE",
                "label":"MAX(week_start)"
              },
              true
            ]
          ],
          "annotation_layers": [],
          "row_limit": 1000,
          "series_limit": 0,
          "series_limit_metric": {
            "aggregate":"MAX",
            "column":{"column_name":"week_start","id":458,"is_dttm":true,"type":"Nullable(Date)"},
            "expressionType":"SIMPLE",
            "label":"MAX(week_start)"
          },
          "order_desc": false,
          "url_params": {"slice_id":"412"},
          "custom_params": {},
          "custom_form_data": {},
          "post_processing": [],
          "time_offsets": []
        }
      ],
      "form_data": {
        "datasource":"38__table",
        "viz_type":"table",
        "slice_id":412,
        "query_mode":"aggregate",
        "groupby":[{ "expressionType":"SQL", "label":"Week", "sqlExpression":"week_label" }],
        "time_grain_sqla":"P1D",
        "temporal_columns_lookup":{"week_start":true,"week_end":true},
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"POSVolume","id":461,"type":"Nullable(Float64)"},
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"POS Volume (TL)"
          },
          {
            "aggregate":"SUM",
            "column":{"column_name":"LiveNewMerchant","id":462,"type":"UInt64"},
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Live (New Merchant)"
          }
        ],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"week_start",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today())"
          }
        ],
        "timeseries_limit_metric":{
          "aggregate":"MAX",
          "column":{"column_name":"week_start","id":458,"is_dttm":true,"type":"Nullable(Date)"},
          "expressionType":"SIMPLE",
          "label":"MAX(week_start)"
        },
        "order_desc": false,
        "row_limit": 1000,
        "table_timestamp_format":"smart_date",
        "allow_render_html": true,
        "column_config":{
          "Live (New Merchant)":{"horizontalAlign":"center"},
          "POS Volume (TL)":{"d3NumberFormat":",d","d3SmallNumberFormat":",d","horizontalAlign":"center"},
          "Week":{"horizontalAlign":"center"}
        },
        "show_cell_bars": false,
        "color_pn": true,
        "comparison_color_scheme":"Green",
        "comparison_type":"values",
        "extra_form_data": {},
        "force": true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget18Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A420%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": {"id":46,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "time_grain_sqla":"P1D",
            "having":"",
            "where":"(month_start >= toStartOfMonth(today()) - INTERVAL 3 MONTHS)"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "timeGrain":"P1D",
              "columnType":"BASE_AXIS",
              "expressionType":"SQL",
              "label":"Months",
              "sqlExpression":"month_label"
            }
          ],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"POSVolume","id":461,"type":"Nullable(Float64)"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"POS Volume (TL)"
            },
            {
              "expressionType":"SQL",
              "label":"MAX(replaceAll(toString(month_start), '-', ''))",
              "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
            }
          ],
          "orderby": [
            [
              {
                "expressionType":"SQL",
                "label":"MAX(replaceAll(toString(month_start), '-', ''))",
                "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
              },
              true
            ]
          ],
          "annotation_layers": [],
          "row_limit": 1000,
          "series_columns": [],
          "series_limit": 0,
          "series_limit_metric": {
            "expressionType":"SQL",
            "label":"MAX(replaceAll(toString(month_start), '-', ''))",
            "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
          },
          "order_desc": false,
          "url_params": {"slice_id":"420"},
          "custom_params": {},
          "custom_form_data": {},
          "time_offsets": [],
          "post_processing": [
            {
              "operation":"pivot",
              "options":{
                "index":["Months"],
                "columns":[],
                "aggregates":{
                  "POS Volume (TL)":{"operator":"mean"},
                  "MAX(replaceAll(toString(month_start), '-', ''))":{"operator":"mean"}
                },
                "drop_missing_columns":false
              }
            },
            {
              "operation":"sort",
              "options":{"by":"MAX(replaceAll(toString(month_start), '-', ''))","ascending":true}
            },
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data": {
        "datasource":"46__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":420,
        "x_axis":{"expressionType":"SQL","label":"Months","sqlExpression":"month_label"},
        "time_grain_sqla":"P1D",
        "x_axis_sort":"MAX(replaceAll(toString(month_start), '-', ''))",
        "x_axis_sort_asc": true,
        "metrics": [
          {
            "aggregate":"SUM",
            "column":{"column_name":"POSVolume","id":461,"type":"Nullable(Float64)"},
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"POS Volume (TL)"
          }
        ],
        "groupby": [],
        "adhoc_filters": [
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"week_start",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"month_start >= toStartOfMonth(today()) - INTERVAL 3 MONTHS"
          }
        ],
        "timeseries_limit_metric": {
          "expressionType":"SQL",
          "label":"MAX(replaceAll(toString(month_start), '-', ''))",
          "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
        },
        "order_desc": false,
        "row_limit": 1000,
        "truncate_metric": true,
        "show_empty_columns": true,
        "comparison_type":"values",
        "annotation_layers": [],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"vertical",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"sum",
        "color_scheme":"supersetColors",
        "show_value": true,
        "only_total": true,
        "show_legend": true,
        "legendType":"scroll",
        "legendOrientation":"top",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":45,
        "y_axis_format":"SMART_NUMBER",
        "truncateXAxis": true,
        "y_axis_bounds":[null, null],
        "rich_tooltip": true,
        "showTooltipTotal": true,
        "showTooltipPercentage": true,
        "tooltipTimeFormat":"smart_date",
        "extra_form_data": {},
        "force": true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget19Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A406%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config’den al

        String body = """
    {
      "datasource": {"id":46,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "having": "",
            "where": "(month_start >= toStartOfMonth(today()) - INTERVAL 3 MONTHS)"
          },
          "applied_time_extras": {},
          "columns": [
            { "expressionType":"SQL", "label":"Month", "sqlExpression":"month_label" }
          ],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"POSVolume","id":461,"type":"Nullable(Float64)"},
              "expressionType":"SIMPLE",
              "hasCustomLabel": true,
              "label":"POS Volume (TL)"
            },
            {
              "aggregate":"SUM",
              "column":{"column_name":"LiveNewMerchant","id":462,"type":"UInt64"},
              "expressionType":"SIMPLE",
              "hasCustomLabel": true,
              "label":"Live (New Merchant)"
            }
          ],
          "orderby": [
            [
              {
                "expressionType":"SQL",
                "label":"MAX(replaceAll(toString(month_start), '-', ''))",
                "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
              },
              true
            ]
          ],
          "annotation_layers": [],
          "row_limit": 1000,
          "series_limit": 0,
          "series_limit_metric": {
            "expressionType":"SQL",
            "label":"MAX(replaceAll(toString(month_start), '-', ''))",
            "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
          },
          "order_desc": false,
          "url_params": {"slice_id":"406"},
          "custom_params": {},
          "custom_form_data": {},
          "post_processing": [],
          "time_offsets": []
        }
      ],
      "form_data": {
        "datasource":"46__table",
        "viz_type":"table",
        "slice_id":406,
        "query_mode":"aggregate",
        "groupby":[{ "expressionType":"SQL", "label":"Month", "sqlExpression":"month_label" }],
        "time_grain_sqla":"P1D",
        "temporal_columns_lookup":{"month_start":true,"month_end":true},
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"POSVolume","id":461,"type":"Nullable(Float64)"},
            "expressionType":"SIMPLE",
            "hasCustomLabel": true,
            "label":"POS Volume (TL)"
          },
          {
            "aggregate":"SUM",
            "column":{"column_name":"LiveNewMerchant","id":462,"type":"UInt64"},
            "expressionType":"SIMPLE",
            "hasCustomLabel": true,
            "label":"Live (New Merchant)"
          }
        ],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"week_start",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"month_start >= toStartOfMonth(today()) - INTERVAL 3 MONTHS"
          }
        ],
        "timeseries_limit_metric": {
          "expressionType":"SQL",
          "label":"MAX(replaceAll(toString(month_start), '-', ''))",
          "sqlExpression":"MAX(replaceAll(toString(month_start), '-', ''))"
        },
        "order_desc": false,
        "row_limit": 1000,
        "table_timestamp_format":"smart_date",
        "allow_render_html": true,
        "column_config":{
          "Live (New Merchant)":{"horizontalAlign":"center"},
          "Month":{"horizontalAlign":"center"},
          "POS Volume (TL)":{"d3NumberFormat":",d","d3SmallNumberFormat":",d","horizontalAlign":"center"}
        },
        "show_cell_bars": false,
        "color_pn": true,
        "comparison_color_scheme":"Green",
        "comparison_type":"values",
        "extra_form_data": {},
        "force": true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget22Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A435%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": {"id":47,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters":[{"col":"company_name","op":"NOT IN","val":["Test merchant"]}],
          "extras":{
            "time_grain_sqla":"P1M",
            "having":"",
            "where":"(month_start >= toStartOfMonth(today()) - INTERVAL 6 MONTHS)"
          },
          "applied_time_extras":{},
          "columns":[
            {"timeGrain":"P1M","columnType":"BASE_AXIS","expressionType":"SQL","sqlExpression":"month_start","label":"month_start"},
            {"expressionType":"SQL","label":"Merchant Name","sqlExpression":"company_name"}
          ],
          "metrics":[
            {
              "aggregate":"SUM",
              "column":{"column_name":"posvolume","id":470,"type":"Nullable(Float64)"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Volume (TL)"
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"posvolume","id":470,"type":"Nullable(Float64)"},
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Volume (TL)"
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":120,
          "series_columns":[{"expressionType":"SQL","label":"Merchant Name","sqlExpression":"company_name"}],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"435"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {
              "operation":"pivot",
              "options":{
                "index":["month_start"],
                "columns":["Merchant Name"],
                "aggregates":{"Volume (TL)":{"operator":"mean"}},
                "drop_missing_columns":false
              }
            },
            {"operation":"rename","options":{"columns":{"Volume (TL)":null},"level":0,"inplace":true}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"47__table",
        "viz_type":"echarts_timeseries_line",
        "slice_id":435,
        "x_axis":"month_start",
        "time_grain_sqla":"P1M",
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"posvolume","id":470,"type":"Nullable(Float64)"},
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Volume (TL)"
          }
        ],
        "groupby":[{"expressionType":"SQL","label":"Merchant Name","sqlExpression":"company_name"}],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"month_start",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"month_start >= toStartOfMonth(today()) - INTERVAL 6 MONTHS"
          },
          {
            "clause":"WHERE",
            "expressionType":"SIMPLE",
            "subject":"company_name",
            "operator":"NOT_IN",
            "comparator":["Test merchant"]
          }
        ],
        "row_limit":"120",
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"sum",
        "sort_series_ascending":false,
        "color_scheme":"supersetColors",
        "seriesType":"line",
        "show_value":false,
        "only_total":true,
        "opacity":0.2,
        "markerEnabled":true,
        "markerSize":6,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"bottom",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":45,
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "y_axis_format":"SMART_NUMBER",
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget23Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A415%7D";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // session=<...> değeri

        String body = """
    {
      "datasource": {"id": 40, "type": "table"},
      "force": false,
      "queries": [
        {
          "filters": [
            {"col": "company_name", "op": "NOT IN", "val": ["Test merchant"]}
          ],
          "extras": {
            "having": "",
            "where": "(month_start < toStartOfMonth(toStartOfMonth(today())-1) AND month_start >= toStartOfMonth(toStartOfMonth(toStartOfMonth(today())-1)-1))"
          },
          "applied_time_extras": {},
          "columns": [
            {"datasourceWarning": false, "expressionType": "SQL", "label": "Month", "sqlExpression": "CASE \\r\\n    WHEN toMonth(month_start) = 1 THEN 'January'\\r\\n    WHEN toMonth(month_start) = 2 THEN 'February'\\r\\n    WHEN toMonth(month_start) = 3 THEN 'March'\\r\\n    WHEN toMonth(month_start) = 4 THEN 'April'\\r\\n    WHEN toMonth(month_start) = 5 THEN 'May'\\r\\n    WHEN toMonth(month_start) = 6 THEN 'June'\\r\\n    WHEN toMonth(month_start) = 7 THEN 'July'\\r\\n    WHEN toMonth(month_start) = 8 THEN 'August'\\r\\n    WHEN toMonth(month_start) = 9 THEN 'September'\\r\\n    WHEN toMonth(month_start) = 10 THEN 'October'\\r\\n    WHEN toMonth(month_start) = 11 THEN 'November'\\r\\n    WHEN toMonth(month_start) = 12 THEN 'December'\\r\\nEND"},
            {"datasourceWarning": false, "expressionType": "SQL", "label": "Merchant Name", "sqlExpression": "company_name"},
            {"datasourceWarning": false, "expressionType": "SQL", "label": "# Of Trx", "sqlExpression": "trx_count"},
            {"datasourceWarning": false, "expressionType": "SQL", "label": "Volume (TL)", "sqlExpression": "posvolume"}
          ],
          "orderby": [],
          "annotation_layers": [],
          "row_limit": 30,
          "series_limit": 0,
          "order_desc": true,
          "url_params": {
            "dashboard_page_id": "MgZI7Yvj6TzEPtC9kExvN",
            "form_data_key": "c5n1i9NwiUe2_7CKwUk552437w3KQRmnoCdqI8ZC2xqDh3Alp6TJwB-woK3s09uo",
            "slice_id": "415"
          },
          "custom_params": {},
          "custom_form_data": {},
          "post_processing": [],
          "time_offsets": []
        }
      ],
      "form_data": {
        "datasource": "40__table",
        "viz_type": "table",
        "slice_id": 415,
        "url_params": {
          "dashboard_page_id": "MgZI7Yvj6TzEPtC9kExvN",
          "form_data_key": "c5n1i9NwiUe2_7CKwUk552437w3KQRmnoCdqI8ZC2xqDh3Alp6TJwB-woK3s09uo",
          "slice_id": "415"
        },
        "query_mode": "raw",
        "groupby": [],
        "temporal_columns_lookup": {"month_start": true},
        "all_columns": [
          {"datasourceWarning": false, "expressionType": "SQL", "label": "Month", "sqlExpression": "CASE \\r\\n    WHEN toMonth(month_start) = 1 THEN 'January'\\r\\n    WHEN toMonth(month_start) = 2 THEN 'February'\\r\\n    WHEN toMonth(month_start) = 3 THEN 'March'\\r\\n    WHEN toMonth(month_start) = 4 THEN 'April'\\r\\n    WHEN toMonth(month_start) = 5 THEN 'May'\\r\\n    WHEN toMonth(month_start) = 6 THEN 'June'\\r\\n    WHEN toMonth(month_start) = 7 THEN 'July'\\r\\n    WHEN toMonth(month_start) = 8 THEN 'August'\\r\\n    WHEN toMonth(month_start) = 9 THEN 'September'\\r\\n    WHEN toMonth(month_start) = 10 THEN 'October'\\r\\n    WHEN toMonth(month_start) = 11 THEN 'November'\\r\\n    WHEN toMonth(month_start) = 12 THEN 'December'\\r\\nEND"},
          {"datasourceWarning": false, "expressionType": "SQL", "label": "Merchant Name", "sqlExpression": "company_name"},
          {"datasourceWarning": false, "expressionType": "SQL", "label": "# Of Trx", "sqlExpression": "trx_count"},
          {"datasourceWarning": false, "expressionType": "SQL", "label": "Volume (TL)", "sqlExpression": "posvolume"}
        ],
        "percent_metrics": [],
        "adhoc_filters": [
          {"clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_gii2uvdv5x9_4ei8d8mm5g3", "isExtra": false, "isNew": false, "operator": "TEMPORAL_RANGE", "sqlExpression": "month_start < toStartOfMonth(toStartOfMonth(today())-1) AND month_start >= toStartOfMonth(toStartOfMonth(toStartOfMonth(today())-1)-1)", "subject": "month_start"},
          {"clause": "WHERE", "comparator": ["Test merchant"], "datasourceWarning": false, "expressionType": "SIMPLE", "filterOptionName": "filter_rrde8t5xof_iy250ablezo", "isExtra": false, "isNew": false, "operator": "NOT IN", "operatorId": "NOT_IN", "sqlExpression": null, "subject": "company_name"}
        ],
        "order_by_cols": [],
        "row_limit": "30",
        "server_page_length": 10,
        "order_desc": true,
        "table_timestamp_format": "smart_date",
        "allow_render_html": true,
        "column_config": {
          "# Of Trx": {"d3NumberFormat": ",d", "d3SmallNumberFormat": ",d", "horizontalAlign": "center"},
          "Merchant Name": {"horizontalAlign": "center"},
          "Month": {"horizontalAlign": "center"},
          "Volume (TL)": {"d3NumberFormat": ",d", "d3SmallNumberFormat": ",d", "horizontalAlign": "center"}
        },
        "show_cell_bars": false,
        "color_pn": true,
        "comparison_color_scheme": "Green",
        "conditional_formatting": [],
        "comparison_type": "values",
        "extra_form_data": {},
        "force": false,
        "result_format": "json",
        "result_type": "full",
        "include_time": false
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget24Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A410%7D";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // properties: cookie=session=<...>

        String body = """
    {
      "datasource": {"id": 40, "type": "table"},
      "force": false,
      "queries": [
        {
          "filters": [
            {"col": "company_name", "op": "NOT IN", "val": ["Test merchant"]}
          ],
          "extras": {
            "having": "",
            "where": "(month_start < toStartOfMonth(today()) AND month_start >= toStartOfMonth(toStartOfMonth(today())-1))"
          },
          "applied_time_extras": {},
          "columns": [
            {"datasourceWarning": false, "expressionType": "SQL", "label": "Month", "sqlExpression": "CASE \\r\\n    WHEN toMonth(month_start) = 1 THEN 'January'\\r\\n    WHEN toMonth(month_start) = 2 THEN 'February'\\r\\n    WHEN toMonth(month_start) = 3 THEN 'March'\\r\\n    WHEN toMonth(month_start) = 4 THEN 'April'\\r\\n    WHEN toMonth(month_start) = 5 THEN 'May'\\r\\n    WHEN toMonth(month_start) = 6 THEN 'June'\\r\\n    WHEN toMonth(month_start) = 7 THEN 'July'\\r\\n    WHEN toMonth(month_start) = 8 THEN 'August'\\r\\n    WHEN toMonth(month_start) = 9 THEN 'September'\\r\\n    WHEN toMonth(month_start) = 10 THEN 'October'\\r\\n    WHEN toMonth(month_start) = 11 THEN 'November'\\r\\n    WHEN toMonth(month_start) = 12 THEN 'December'\\r\\nEND"},
            {"datasourceWarning": false, "expressionType": "SQL", "label": "Merchant Name", "sqlExpression": "company_name"},
            {"datasourceWarning": false, "expressionType": "SQL", "label": "# Of Trx", "sqlExpression": "trx_count"},
            {"datasourceWarning": false, "expressionType": "SQL", "label": "Volume (TL)", "sqlExpression": "posvolume"}
          ],
          "orderby": [],
          "annotation_layers": [],
          "row_limit": 30,
          "series_limit": 0,
          "order_desc": true,
          "url_params": {
            "dashboard_page_id": "MgZI7Yvj6TzEPtC9kExvN",
            "form_data_key": "CMAcKJnwbJK-NOxPKGCOSWhIqAoaFWOPb4GnUjfhWTejB_E_Jk-Q7-hvRDAMsRC8",
            "slice_id": "410"
          },
          "custom_params": {},
          "custom_form_data": {},
          "post_processing": [],
          "time_offsets": []
        }
      ],
      "form_data": {
        "datasource": "40__table",
        "viz_type": "table",
        "slice_id": 410,
        "url_params": {
          "dashboard_page_id": "MgZI7Yvj6TzEPtC9kExvN",
          "form_data_key": "CMAcKJnwbJK-NOxPKGCOSWhIqAoaFWOPb4GnUjfhWTejB_E_Jk-Q7-hvRDAMsRC8",
          "slice_id": "410"
        },
        "query_mode": "raw",
        "groupby": [],
        "temporal_columns_lookup": {"month_start": true},
        "all_columns": [
          {"datasourceWarning": false, "expressionType": "SQL", "label": "Month", "sqlExpression": "CASE \\r\\n    WHEN toMonth(month_start) = 1 THEN 'January'\\r\\n    WHEN toMonth(month_start) = 2 THEN 'February'\\r\\n    WHEN toMonth(month_start) = 3 THEN 'March'\\r\\n    WHEN toMonth(month_start) = 4 THEN 'April'\\r\\n    WHEN toMonth(month_start) = 5 THEN 'May'\\r\\n    WHEN toMonth(month_start) = 6 THEN 'June'\\r\\n    WHEN toMonth(month_start) = 7 THEN 'July'\\r\\n    WHEN toMonth(month_start) = 8 THEN 'August'\\r\\n    WHEN toMonth(month_start) = 9 THEN 'September'\\r\\n    WHEN toMonth(month_start) = 10 THEN 'October'\\r\\n    WHEN toMonth(month_start) = 11 THEN 'November'\\r\\n    WHEN toMonth(month_start) = 12 THEN 'December'\\r\\nEND"},
          {"datasourceWarning": false, "expressionType": "SQL", "label": "Merchant Name", "sqlExpression": "company_name"},
          {"datasourceWarning": false, "expressionType": "SQL", "label": "# Of Trx", "sqlExpression": "trx_count"},
          {"datasourceWarning": false, "expressionType": "SQL", "label": "Volume (TL)", "sqlExpression": "posvolume"}
        ],
        "percent_metrics": [],
        "adhoc_filters": [
          {"clause": "WHERE", "comparator": null, "datasourceWarning": false, "expressionType": "SQL", "filterOptionName": "filter_gii2uvdv5x9_4ei8d8mm5g3", "isExtra": false, "isNew": false, "operator": "TEMPORAL_RANGE", "sqlExpression": "month_start < toStartOfMonth(today()) AND month_start >= toStartOfMonth(toStartOfMonth(today())-1)", "subject": "month_start"},
          {"clause": "WHERE", "comparator": ["Test merchant"], "datasourceWarning": false, "expressionType": "SIMPLE", "filterOptionName": "filter_vuhsw5p9h8n_xybdggb96bl", "isExtra": false, "isNew": false, "operator": "NOT IN", "operatorId": "NOT_IN", "sqlExpression": null, "subject": "company_name"}
        ],
        "order_by_cols": [],
        "row_limit": "30",
        "server_page_length": 10,
        "order_desc": true,
        "table_timestamp_format": "smart_date",
        "allow_render_html": true,
        "column_config": {
          "# Of Trx": {"d3NumberFormat": ",d", "d3SmallNumberFormat": ",d", "horizontalAlign": "center"},
          "Merchant Name": {"horizontalAlign": "center"},
          "Month": {"horizontalAlign": "center"},
          "Volume (TL)": {"d3NumberFormat": ",d", "d3SmallNumberFormat": ",d", "horizontalAlign": "center"}
        },
        "show_cell_bars": false,
        "color_pn": true,
        "comparison_color_scheme": "Green",
        "conditional_formatting": [],
        "comparison_type": "values",
        "extra_form_data": {},
        "force": false,
        "result_format": "json",
        "result_type": "full",
        "include_time": false
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget25Request() throws Exception {

        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A409%7D";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource": {"id":40,"type":"table"},
      "force": false,
      "queries":[
        {
          "filters":[
            {"col":"company_name","op":"NOT IN","val":["Test merchant"]}
          ],
          "extras":{
            "having":"",
            "where":"(month_start >= toStartOfMonth(today()) AND month_start <toStartOfWeek(today()))"
          },
          "applied_time_extras":{},
          "columns":[
            {"expressionType":"SQL","label":"Month","sqlExpression":
              "CASE \\r\\n    WHEN toMonth(month_start) = 1 THEN 'January'\\r\\n    WHEN toMonth(month_start) = 2 THEN 'February'\\r\\n    WHEN toMonth(month_start) = 3 THEN 'March'\\r\\n    WHEN toMonth(month_start) = 4 THEN 'April'\\r\\n    WHEN toMonth(month_start) = 5 THEN 'May'\\r\\n    WHEN toMonth(month_start) = 6 THEN 'June'\\r\\n    WHEN toMonth(month_start) = 7 THEN 'July'\\r\\n    WHEN toMonth(month_start) = 8 THEN 'August'\\r\\n    WHEN toMonth(month_start) = 9 THEN 'September'\\r\\n    WHEN toMonth(month_start) = 10 THEN 'October'\\r\\n    WHEN toMonth(month_start) = 11 THEN 'November'\\r\\n    WHEN toMonth(month_start) = 12 THEN 'December'\\r\\nEND"},
            {"expressionType":"SQL","label":"Merchant Name","sqlExpression":"company_name"},
            {"expressionType":"SQL","label":"# Of Trx","sqlExpression":"trx_count"},
            {"expressionType":"SQL","label":"Volume (TL)","sqlExpression":"posvolume"}
          ],
          "orderby":[],
          "annotation_layers":[],
          "row_limit":30,
          "series_limit":0,
          "order_desc":true,
          "url_params":{
            "dashboard_page_id":"MgZI7Yvj6TzEPtC9kExvN",
            "form_data_key":"ETVmzp0tvt54B5Auy7Xf9_wI-XJMywnyFK_pPPtZo5b8gWllBb48qewozjaF0kV2",
            "slice_id":"409"
          },
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        }
      ],
      "form_data":{
        "datasource":"40__table",
        "viz_type":"table",
        "slice_id":409,
        "url_params":{
          "dashboard_page_id":"MgZI7Yvj6TzEPtC9kExvN",
          "form_data_key":"ETVmzp0tvt54B5Auy7Xf9_wI-XJMywnyFK_pPPtZo5b8gWllBb48qewozjaF0kV2",
          "slice_id":"409"
        },
        "query_mode":"raw",
        "groupby":[],
        "temporal_columns_lookup":{"month_start":true},
        "metrics":[],
        "all_columns":[
          {"expressionType":"SQL","label":"Month","sqlExpression":
            "CASE \\r\\n    WHEN toMonth(month_start) = 1 THEN 'January'\\r\\n    WHEN toMonth(month_start) = 2 THEN 'February'\\r\\n    WHEN toMonth(month_start) = 3 THEN 'March'\\r\\n    WHEN toMonth(month_start) = 4 THEN 'April'\\r\\n    WHEN toMonth(month_start) = 5 THEN 'May'\\r\\n    WHEN toMonth(month_start) = 6 THEN 'June'\\r\\n    WHEN toMonth(month_start) = 7 THEN 'July'\\r\\n    WHEN toMonth(month_start) = 8 THEN 'August'\\r\\n    WHEN toMonth(month_start) = 9 THEN 'September'\\r\\n    WHEN toMonth(month_start) = 10 THEN 'October'\\r\\n    WHEN toMonth(month_start) = 11 THEN 'November'\\r\\n    WHEN toMonth(month_start) = 12 THEN 'December'\\r\\nEND"},
          {"expressionType":"SQL","label":"Merchant Name","sqlExpression":"company_name"},
          {"expressionType":"SQL","label":"# Of Trx","sqlExpression":"trx_count"},
          {"expressionType":"SQL","label":"Volume (TL)","sqlExpression":"posvolume"}
        ],
        "percent_metrics":[],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"month_start >= toStartOfMonth(today()) AND month_start <toStartOfWeek(today())",
            "subject":"month_start"
          },
          {
            "clause":"WHERE",
            "operator":"NOT IN",
            "comparator":["Test merchant"],
            "subject":"company_name"
          }
        ],
        "order_by_cols":[],
        "row_limit":"30",
        "server_page_length":10,
        "order_desc":true,
        "table_timestamp_format":"smart_date",
        "allow_render_html":true,
        "column_config":{
          "# Of Trx":{"d3NumberFormat":",d","d3SmallNumberFormat":",d","horizontalAlign":"center"},
          "Merchant Name":{"horizontalAlign":"center"},
          "Month":{"horizontalAlign":"center"},
          "Volume (TL)":{"d3NumberFormat":",.2f","d3SmallNumberFormat":",.2f","horizontalAlign":"center"}
        },
        "show_cell_bars":false,
        "color_pn":true,
        "comparison_color_scheme":"Green",
        "conditional_formatting":[],
        "comparison_type":"values",
        "extra_form_data":{},
        "force":false,
        "result_format":"json",
        "result_type":"full",
        "include_time":false
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "session=" + cookie)
                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Response code: " + response.code());
            }
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget30Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A421%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config’den al

        String body = """
    {
      "datasource": {"id":42,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "having": "",
            "where": "(week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today()))"
          },
          "applied_time_extras": {},
          "columns": [
            { "columnType":"BASE_AXIS", "expressionType":"SQL", "label":"Week", "sqlExpression":"week_label" }
          ],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"live_new_merchants","id":485,"type":"UInt64","type_generic":0},
              "expressionType":"SIMPLE",
              "hasCustomLabel": true,
              "label":"New Merchants"
            },
            {
              "expressionType":"SQL",
              "label":"MAX(replaceAll(toString(week_start), '-', ''))",
              "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
            }
          ],
          "orderby": [
            [
              {
                "expressionType":"SQL",
                "label":"MAX(replaceAll(toString(week_start), '-', ''))",
                "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 1000,
          "series_columns": [],
          "series_limit": 0,
          "series_limit_metric": {
            "expressionType":"SQL",
            "label":"MAX(replaceAll(toString(week_start), '-', ''))",
            "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
          },
          "order_desc": true,
          "url_params": {"slice_id":"421"},
          "custom_params": {},
          "custom_form_data": {},
          "time_offsets": [],
          "post_processing": [
            {
              "operation":"pivot",
              "options":{
                "index":["Week"],
                "columns":[],
                "aggregates":{
                  "New Merchants":{"operator":"mean"},
                  "MAX(replaceAll(toString(week_start), '-', ''))":{"operator":"mean"}
                },
                "drop_missing_columns": false
              }
            },
            { "operation":"sort", "options":{"by":"MAX(replaceAll(toString(week_start), '-', ''))","ascending": true} },
            { "operation":"flatten" }
          ]
        }
      ],
      "form_data": {
        "datasource":"42__table",
        "viz_type":"echarts_timeseries_line",
        "slice_id":421,
        "x_axis": { "expressionType":"SQL", "label":"Week", "sqlExpression":"week_label" },
        "x_axis_sort":"MAX(replaceAll(toString(week_start), '-', ''))",
        "x_axis_sort_asc": true,
        "metrics": [
          {
            "aggregate":"SUM",
            "column":{"column_name":"live_new_merchants","id":485,"type":"UInt64","type_generic":0},
            "expressionType":"SIMPLE",
            "hasCustomLabel": true,
            "label":"New Merchants"
          }
        ],
        "groupby": [],
        "adhoc_filters": [
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"week_start",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today())"
          }
        ],
        "timeseries_limit_metric": {
          "expressionType":"SQL",
          "label":"MAX(replaceAll(toString(week_start), '-', ''))",
          "sqlExpression":"MAX(replaceAll(toString(week_start), '-', ''))"
        },
        "order_desc": true,
        "row_limit": 1000,
        "truncate_metric": true,
        "show_empty_columns": true,
        "comparison_type":"values",
        "annotation_layers": [],
        "forecastPeriods": 10,
        "forecastInterval": 0.8,
        "x_axis_title_margin": 15,
        "y_axis_title_margin": 15,
        "y_axis_title_position":"Left",
        "sort_series_type":"sum",
        "color_scheme":"supersetColors",
        "seriesType":"line",
        "show_value": true,
        "only_total": true,
        "opacity": 0.2,
        "markerSize": 6,
        "show_legend": true,
        "legendType":"scroll",
        "legendOrientation":"top",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation": 45,
        "rich_tooltip": true,
        "showTooltipTotal": true,
        "showTooltipPercentage": true,
        "tooltipTimeFormat":"smart_date",
        "y_axis_format":"SMART_NUMBER",
        "truncateXAxis": true,
        "y_axis_bounds":[null, null],
        "extra_form_data": {},
        "force": true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget31Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A416%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config’den al

        String body = """
    {
      "datasource": {"id":42,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [],
          "extras": {
            "having": "",
            "where": "(week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today()))"
          },
          "applied_time_extras": {},
          "columns": [
            { "expressionType":"SQL", "label":"Week", "sqlExpression":"week_label" }
          ],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"live_new_merchants","id":485,"type":"UInt64","type_generic":0},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"New Merchants",
              "optionName":"metric_jebmnygtfqp_mlii2oiu4sn",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"live_new_merchants","id":485,"type":"UInt64","type_generic":0},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"New Merchants",
                "optionName":"metric_jebmnygtfqp_mlii2oiu4sn",
                "sqlExpression":null
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":1000,
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"416"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        },
        {
          "filters": [],
          "extras": {
            "having": "",
            "where": "(week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today()))"
          },
          "applied_time_extras": {},
          "columns": [],
          "metrics": [
            {
              "aggregate":"SUM",
              "column":{"column_name":"live_new_merchants","id":485,"type":"UInt64","type_generic":0},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"New Merchants",
              "optionName":"metric_jebmnygtfqp_mlii2oiu4sn",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "row_limit":0,
          "row_offset":0,
          "series_limit":0,
          "url_params":{"slice_id":"416"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        }
      ],
      "form_data": {
        "datasource":"42__table",
        "viz_type":"table",
        "slice_id":416,
        "query_mode":"aggregate",
        "groupby":[{"expressionType":"SQL","label":"Week","sqlExpression":"week_label"}],
        "temporal_columns_lookup":{"week_start":true,"week_end":true},
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"live_new_merchants","id":485,"type":"UInt64","type_generic":0},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"New Merchants",
            "optionName":"metric_jebmnygtfqp_mlii2oiu4sn",
            "sqlExpression":null
          }
        ],
        "all_columns":[
          {"expressionType":"SQL","label":"Week","sqlExpression":"week_label"},
          {"expressionType":"SQL","label":"New Merchants","sqlExpression":"live_new_merchants"}
        ],
        "percent_metrics":[],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "datasourceWarning":false,
            "expressionType":"SQL",
            "filterOptionName":"filter_18vnbi5td2h_xd0ccixb9ze",
            "isExtra":false,
            "isNew":false,
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"week_start >= toStartOfMonth(toStartOfMonth(today())-1) AND week_start <toStartOfWeek(today())",
            "subject":"week_start"
          }
        ],
        "order_by_cols":["[\\"week_start\\", true]"],
        "row_limit":1000,
        "order_desc":true,
        "show_totals":true,
        "table_timestamp_format":"smart_date",
        "allow_render_html":true,
        "column_config":{
          "New Merchants":{"horizontalAlign":"center"},
          "Referred Lead":{"horizontalAlign":"center"},
          "Week":{"horizontalAlign":"center"}
        },
        "show_cell_bars":false,
        "color_pn":true,
        "comparison_color_scheme":"Green",
        "conditional_formatting":[],
        "comparison_type":"values",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String resp = response.body().string();
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget32Request() throws Exception {

        String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A422%7D";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        MediaType JSON = MediaType.parse("application/json");

        String bodyStr = """
    {
      "datasource":{"id":4,"type":"table"},
      "force":false,
      "queries":[
        {
          "filters":[
            {"col":"Expected Activation Month","op":"TEMPORAL_RANGE","val":"No filter"}
          ],
          "extras":{
            "having":"",
            "where":"(Partner IS NOT NULL) AND (DealCreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND DealCreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date)))"
          },
          "applied_time_extras":{},
          "columns":[
            {
              "columnType":"BASE_AXIS",
              "expressionType":"SQL",
              "label":"Week",
              "sqlExpression":"CONCAT(\\r\\n  FORMAT(\\r\\n    DATEADD(WEEK, DATEDIFF(WEEK, 0, DealCreatedOn), 0),\\r\\n    'd MMMM', 'en-US'\\r\\n  ),\\r\\n  N' – ',\\r\\n  FORMAT(\\r\\n    DATEADD(DAY, 6, DATEADD(WEEK, DATEDIFF(WEEK, 0, DealCreatedOn), 0)),\\r\\n    'd MMMM', 'en-US'\\r\\n  )\\r\\n)\\r\\n"
            }
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"deal_id","id":232,"type":"INT"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Referred Leads"
            },
            {
              "aggregate":"MAX",
              "column":{"column_name":"CreatedOn","id":70,"type":"DATETIME"},
              "expressionType":"SIMPLE",
              "label":"MAX(CreatedOn)"
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"MAX",
                "column":{"column_name":"CreatedOn","id":70,"type":"DATETIME"},
                "expressionType":"SIMPLE",
                "label":"MAX(CreatedOn)"
              },
              false
            ]
          ],
          "row_limit":1000,
          "series_columns":[],
          "series_limit":0,
          "post_processing":[
            {
              "operation":"pivot",
              "options":{
                "index":["Week"],
                "columns":[],
                "aggregates":{
                  "Referred Leads":{"operator":"mean"},
                  "MAX(CreatedOn)":{"operator":"mean"}
                }
              }
            },
            {
              "operation":"sort",
              "options":{"by":"MAX(CreatedOn)","ascending":true}
            },
            {"operation":"flatten"}
          ]
        }
      ],
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(bodyStr, JSON))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("widget32 request failed: " + response);
            }
            return new JSONObject(response.body().string());
        }
    }


    public static JSONObject sendWidget33Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A417%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config’den al

        String body = """
    {
      "datasource": {"id":4,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters":[{"col":"Expected Activation Month","op":"TEMPORAL_RANGE","val":"No filter"}],
          "extras":{
            "having":"",
            "where":"(Partner IS NOT NULL) AND (DealCreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND DealCreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date)))"
          },
          "applied_time_extras":{},
          "columns":[
            {
              "datasourceWarning":false,
              "expressionType":"SQL",
              "label":"Week",
              "sqlExpression":"CONCAT(\\r\\n  FORMAT(\\r\\n    DATEADD(WEEK, DATEDIFF(WEEK, 0, DealCreatedOn), 0),\\r\\n    'd MMMM', 'en-US'\\r\\n  ),\\r\\n  N' – ',\\r\\n  FORMAT(\\r\\n    DATEADD(DAY, 6, DATEADD(WEEK, DATEDIFF(WEEK, 0, DealCreatedOn), 0)),\\r\\n    'd MMMM', 'en-US'\\r\\n  )\\r\\n)\\r\\n"
            }
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Referred Leads",
              "optionName":"metric_6o31cn49chx_7u2qqtp6oih",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"MAX",
                "column":{"column_name":"CreatedOn","id":70,"is_dttm":true,"type":"DATETIME","type_generic":2},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":false,
                "label":"MAX(CreatedOn)",
                "optionName":"metric_ebcwlfsjuds_gc0zf42navt",
                "sqlExpression":null
              },
              true
            ]
          ],
          "annotation_layers":[],
          "row_limit":1000,
          "series_limit":0,
          "series_limit_metric":{
            "aggregate":"MAX",
            "column":{"column_name":"CreatedOn","id":70,"is_dttm":true,"type":"DATETIME","type_generic":2},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":false,
            "label":"MAX(CreatedOn)",
            "optionName":"metric_ebcwlfsjuds_gc0zf42navt",
            "sqlExpression":null
          },
          "order_desc":false,
          "url_params":{"slice_id":"417"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        },
        {
          "filters":[{"col":"Expected Activation Month","op":"TEMPORAL_RANGE","val":"No filter"}],
          "extras":{
            "having":"",
            "where":"(Partner IS NOT NULL) AND (DealCreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND DealCreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date)))"
          },
          "applied_time_extras":{},
          "columns":[],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Referred Leads",
              "optionName":"metric_6o31cn49chx_7u2qqtp6oih",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "row_limit":0,
          "row_offset":0,
          "series_limit":0,
          "series_limit_metric":{
            "aggregate":"MAX",
            "column":{"column_name":"CreatedOn","id":70,"is_dttm":true,"type":"DATETIME","type_generic":2},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":false,
            "label":"MAX(CreatedOn)",
            "optionName":"metric_ebcwlfsjuds_gc0zf42navt",
            "sqlExpression":null
          },
          "url_params":{"slice_id":"417"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        }
      ],
      "form_data":{
        "datasource":"4__table",
        "viz_type":"table",
        "slice_id":417,
        "query_mode":"aggregate",
        "groupby":[
          {
            "datasourceWarning":false,
            "expressionType":"SQL",
            "label":"Week",
            "sqlExpression":"CONCAT(\\r\\n  FORMAT(\\r\\n    DATEADD(WEEK, DATEDIFF(WEEK, 0, DealCreatedOn), 0),\\r\\n    'd MMMM', 'en-US'\\r\\n  ),\\r\\n  N' – ',\\r\\n  FORMAT(\\r\\n    DATEADD(DAY, 6, DATEADD(WEEK, DATEDIFF(WEEK, 0, DealCreatedOn), 0)),\\r\\n    'd MMMM', 'en-US'\\r\\n  )\\r\\n)\\r\\n"
          }
        ],
        "time_grain_sqla":"P1D",
        "temporal_columns_lookup":{"Expected Activation Month":true,"CreatedOn":true,"DealCreatedOn":true},
        "metrics":[
          {
            "aggregate":"COUNT_DISTINCT",
            "column":{"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Referred Leads",
            "optionName":"metric_6o31cn49chx_7u2qqtp6oih",
            "sqlExpression":null
          }
        ],
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"Expected Activation Month","operator":"TEMPORAL_RANGE","comparator":"No filter"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"Partner IS NOT NULL"},
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"CreatedOn",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"DealCreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND DealCreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))"
          }
        ],
        "timeseries_limit_metric":{
          "aggregate":"MAX",
          "column":{"column_name":"CreatedOn","id":70,"is_dttm":true,"type":"DATETIME","type_generic":2},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":false,
          "label":"MAX(CreatedOn)",
          "optionName":"metric_ebcwlfsjuds_gc0zf42navt",
          "sqlExpression":null
        },
        "order_desc": false,
        "row_limit": 1000,
        "show_totals": true,
        "table_timestamp_format":"smart_date",
        "allow_render_html": true,
        "column_config":{
          "Referred Leads":{"horizontalAlign":"center"},
          "Week":{"horizontalAlign":"center"}
        },
        "color_pn": true,
        "comparison_color_scheme":"Green",
        "comparison_type":"values",
        "force": true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget34Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A423%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // properties’ten al

        String body = """
    {
      "datasource": { "id": 4, "type": "table" },
      "force": true,
      "queries": [
        {
          "filters": [
            { "col": "Expected Activation Month", "op": "TEMPORAL_RANGE", "val": "No filter" },
            { "col": "Partner", "op": "NOT IN", "val": ["Not Defined", "TANIMLI_DEGIL", null] },
            { "col": "Sales Stage", "op": "NOT IN", "val": [null, "LeadRet", "ContractRet", "PitchedRet", "Ret", "ProspectRet", "Live"] }
          ],
          "extras": {
            "having": "",
            "where": "(CreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND CreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date)))"
          },
          "applied_time_extras": {},
          "columns": ["Partner"],
          "metrics": [
            {
              "aggregate": "COUNT_DISTINCT",
              "column": { "column_name": "deal_id", "id": 232, "type": "INT", "type_generic": 0 },
              "datasourceWarning": false,
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Referred Leads",
              "optionName": "metric_6o31cn49chx_7u2qqtp6oih",
              "sqlExpression": null
            }
          ],
          "orderby": [
            [
              {
                "aggregate": "COUNT_DISTINCT",
                "column": { "column_name": "deal_id", "id": 232, "type": "INT", "type_generic": 0 },
                "datasourceWarning": false,
                "expressionType": "SIMPLE",
                "hasCustomLabel": true,
                "label": "Referred Leads",
                "optionName": "metric_6o31cn49chx_7u2qqtp6oih",
                "sqlExpression": null
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 5,
          "series_limit": 0,
          "order_desc": true,
          "url_params": { "slice_id": "423" },
          "custom_params": {},
          "custom_form_data": {}
        }
      ],
      "form_data": {
        "datasource": "4__table",
        "viz_type": "pie",
        "slice_id": 423,
        "url_params": { "slice_id": "423" },
        "groupby": ["Partner"],
        "metric": {
          "aggregate": "COUNT_DISTINCT",
          "column": { "column_name": "deal_id", "id": 232, "type": "INT", "type_generic": 0 },
          "datasourceWarning": false,
          "expressionType": "SIMPLE",
          "hasCustomLabel": true,
          "label": "Referred Leads",
          "optionName": "metric_6o31cn49chx_7u2qqtp6oih",
          "sqlExpression": null
        },
        "adhoc_filters": [
          { "clause": "WHERE", "expressionType": "SIMPLE", "subject": "Expected Activation Month", "operator": "TEMPORAL_RANGE", "comparator": "No filter" },
          { "clause": "WHERE", "expressionType": "SIMPLE", "subject": "Partner", "operator": "NOT IN", "comparator": ["Not Defined", "TANIMLI_DEGIL", null] },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "subject": "CreatedOn",
            "operator": "TEMPORAL_RANGE",
            "sqlExpression": "CreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND CreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))"
          },
          { "clause": "WHERE", "expressionType": "SIMPLE", "subject": "Sales Stage", "operator": "NOT IN", "comparator": [null, "LeadRet", "ContractRet", "PitchedRet", "Ret", "ProspectRet", "Live"] }
        ],
        "row_limit": "5",
        "sort_by_metric": true,
        "color_scheme": "supersetColors",
        "show_labels_threshold": 1,
        "show_legend": true,
        "legendType": "scroll",
        "legendOrientation": "bottom",
        "label_type": "value_percent",
        "number_format": "SMART_NUMBER",
        "date_format": "smart_date",
        "show_labels": true,
        "labels_outside": true,
        "label_line": true,
        "outerRadius": 45,
        "donut": true,
        "innerRadius": 25,
        "extra_form_data": {},
        "force": true,
        "result_format": "json",
        "result_type": "full"
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget35Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A418%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // properties dosyandan al

        String body = """
    {
      "datasource": {"id":4,"type":"table"},
      "force": true,
      "queries": [
        {
          "filters": [
            {"col":"Expected Activation Month","op":"TEMPORAL_RANGE","val":"No filter"},
            {"col":"Partner","op":"NOT IN","val":["Not Defined","TANIMLI_DEGIL",null]},
            {"col":"Sales Stage","op":"NOT IN","val":[null,"LeadRet","ContractRet","PitchedRet","Ret","ProspectRet","Live"]}
          ],
          "extras": {
            "having": "",
            "where": "(CreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND CreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date)))"
          },
          "applied_time_extras": {},
          "columns": ["Partner"],
          "metrics": [
            {
              "aggregate": "COUNT_DISTINCT",
              "column": {"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
              "datasourceWarning": false,
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Referred Leads",
              "optionName": "metric_6o31cn49chx_7u2qqtp6oih",
              "sqlExpression": null
            }
          ],
          "orderby": [
            [
              {
                "aggregate": "COUNT_DISTINCT",
                "column": {"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
                "datasourceWarning": false,
                "expressionType": "SIMPLE",
                "hasCustomLabel": false,
                "label": "COUNT_DISTINCT(deal_id)",
                "optionName": "metric_ebcwlfsjuds_gc0zf42navt",
                "sqlExpression": null
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 5,
          "series_limit": 0,
          "series_limit_metric": {
            "aggregate": "COUNT_DISTINCT",
            "column": {"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": false,
            "label": "COUNT_DISTINCT(deal_id)",
            "optionName": "metric_ebcwlfsjuds_gc0zf42navt",
            "sqlExpression": null
          },
          "order_desc": true,
          "url_params": {"slice_id":"418"},
          "custom_params": {},
          "custom_form_data": {},
          "post_processing": [],
          "time_offsets": []
        },
        {
          "filters": [
            {"col":"Expected Activation Month","op":"TEMPORAL_RANGE","val":"No filter"},
            {"col":"Partner","op":"NOT IN","val":["Not Defined","TANIMLI_DEGIL",null]},
            {"col":"Sales Stage","op":"NOT IN","val":[null,"LeadRet","ContractRet","PitchedRet","Ret","ProspectRet","Live"]}
          ],
          "extras": {
            "having": "",
            "where": "(CreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND CreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date)))"
          },
          "applied_time_extras": {},
          "columns": [],
          "metrics": [
            {
              "aggregate": "COUNT_DISTINCT",
              "column": {"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
              "datasourceWarning": false,
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Referred Leads",
              "optionName": "metric_6o31cn49chx_7u2qqtp6oih",
              "sqlExpression": null
            }
          ],
          "annotation_layers": [],
          "row_limit": 0,
          "row_offset": 0,
          "series_limit": 0,
          "series_limit_metric": {
            "aggregate": "COUNT_DISTINCT",
            "column": {"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": false,
            "label": "COUNT_DISTINCT(deal_id)",
            "optionName": "metric_ebcwlfsjuds_gc0zf42navt",
            "sqlExpression": null
          },
          "url_params": {"slice_id":"418"},
          "custom_params": {},
          "custom_form_data": {},
          "post_processing": [],
          "time_offsets": []
        }
      ],
      "form_data": {
        "datasource": "4__table",
        "viz_type": "table",
        "slice_id": 418,
        "url_params": {"slice_id":"418"},
        "query_mode": "aggregate",
        "groupby": ["Partner"],
        "temporal_columns_lookup": {"Expected Activation Month":true,"CreatedOn":true,"DealCreatedOn":true},
        "metrics": [
          {
            "aggregate": "COUNT_DISTINCT",
            "column": {"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
            "datasourceWarning": false,
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Referred Leads",
            "optionName": "metric_6o31cn49chx_7u2qqtp6oih",
            "sqlExpression": null
          }
        ],
        "adhoc_filters": [
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"Expected Activation Month","operator":"TEMPORAL_RANGE","comparator":"No filter"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"Partner","operator":"NOT IN","comparator":["Not Defined","TANIMLI_DEGIL",null]},
          {"clause":"WHERE","expressionType":"SQL","subject":"CreatedOn","operator":"TEMPORAL_RANGE","sqlExpression":"CreatedOn >= DATEADD(\\r\\n          week, -4,\\r\\n          DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))\\r\\n      )\\r\\n  AND CreatedOn <  DATEADD(day, - ((DATEDIFF(day, 0, CAST(GETDATE() AS date)) + 1) % 7), CAST(GETDATE() AS date))"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"Sales Stage","operator":"NOT IN","comparator":[null,"LeadRet","ContractRet","PitchedRet","Ret","ProspectRet","Live"]}
        ],
        "timeseries_limit_metric": {
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"deal_id","id":232,"type":"INT","type_generic":0},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":false,
          "label":"COUNT_DISTINCT(deal_id)",
          "optionName":"metric_ebcwlfsjuds_gc0zf42navt",
          "sqlExpression":null
        },
        "row_limit":"5",
        "order_desc": true,
        "show_totals": true,
        "table_timestamp_format": "smart_date",
        "allow_render_html": true,
        "column_config": {
          "Partner": {"horizontalAlign":"center"},
          "Referred Leads": {"horizontalAlign":"center"}
        },
        "show_cell_bars": false,
        "color_pn": true,
        "comparison_color_scheme": "Green",
        "extra_form_data": {},
        "force": true,
        "result_format": "json",
        "result_type": "full"
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return new JSONObject(response.body().string());
        }
    }

    public static JSONObject sendWidget44Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A497%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": { "id": 56, "type": "table" },
      "force": true,
      "queries": [
        {
          "filters": [
            {
              "col": "SalesRep",
              "op": "NOT IN",
              "val": ["Test Sipay"]
            }
          ],
          "extras": {
            "having": "",
            "where": "(1=1)"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "columnType": "BASE_AXIS",
              "sqlExpression": "SalesRep",
              "label": "SalesRep",
              "expressionType": "SQL"
            }
          ],
          "metrics": [
            {
              "aggregate": "SUM",
              "column": {
                "column_name": "CustomerCount",
                "id": 793,
                "type": "UInt64"
              },
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Customer Count"
            }
          ],
          "orderby": [
            [
              {
                "aggregate": "SUM",
                "column": {
                  "column_name": "CustomerCount",
                  "id": 793,
                  "type": "UInt64"
                },
                "expressionType": "SIMPLE",
                "label": "Customer Count"
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 50000,
          "series_columns": [],
          "series_limit": 0,
          "order_desc": true,
          "url_params": {
            "slice_id": "497"
          },
          "custom_params": {},
          "custom_form_data": {},
          "time_offsets": [],
          "post_processing": [
            {
              "operation": "pivot",
              "options": {
                "index": ["SalesRep"],
                "columns": [],
                "aggregates": {
                  "Customer Count": {
                    "operator": "mean"
                  }
                },
                "drop_missing_columns": false
              }
            },
            {
              "operation": "sort",
              "options": {
                "is_sort_index": true,
                "ascending": false
              }
            },
            {
              "operation": "flatten"
            }
          ]
        }
      ],
      "form_data": {
        "datasource": "56__table",
        "viz_type": "echarts_timeseries_bar",
        "slice_id": 497,
        "x_axis": "SalesRep",
        "xAxisForceCategorical": true,
        "x_axis_sort": "SalesRep",
        "x_axis_sort_asc": false,
        "metrics": [
          {
            "aggregate": "SUM",
            "column": {
              "column_name": "CustomerCount",
              "id": 793,
              "type": "UInt64"
            },
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count"
          }
        ],
        "groupby": [],
        "adhoc_filters": [
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "1=1",
            "subject": "CreatedOn",
            "operator": "TEMPORAL_RANGE",
            "operatorId": "TEMPORAL_RANGE"
          },
          {
            "clause": "WHERE",
            "expressionType": "SIMPLE",
            "subject": "SalesRep",
            "operator": "NOT IN",
            "operatorId": "NOT_IN",
            "comparator": ["Test Sipay"]
          }
        ],
        "row_limit": 50000
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }


    public static JSONObject sendWidget49Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A429%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie"); // config'den al

        String body = """
    {
      "datasource": { "id": 54, "type": "table" },
      "force": true,
      "queries": [
        {
          "filters": [
            {
              "col": "SalesRep",
              "op": "NOT IN",
              "val": ["Test Sipay"]
            }
          ],
          "extras": {
            "having": "",
            "where": "(1=1) AND (StageAggr IS NOT NULL) AND (StageAggr NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (StageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"
          },
          "applied_time_extras": {},
          "columns": [
            {
              "columnType": "BASE_AXIS",
              "sqlExpression": "SalesRep",
              "label": "SalesRep",
              "expressionType": "SQL"
            },
            {
              "expressionType": "SQL",
              "label": "Sales Stage",
              "sqlExpression": "StageAggr"
            }
          ],
          "metrics": [
            {
              "aggregate": "SUM",
              "column": {
                "column_name": "CustomerCount",
                "id": 789,
                "type": "UInt64"
              },
              "expressionType": "SIMPLE",
              "hasCustomLabel": true,
              "label": "Customer Count"
            }
          ],
          "orderby": [
            [
              {
                "aggregate": "SUM",
                "column": {
                  "column_name": "CustomerCount",
                  "id": 789,
                  "type": "UInt64"
                },
                "expressionType": "SIMPLE",
                "label": "Customer Count"
              },
              false
            ]
          ],
          "annotation_layers": [],
          "row_limit": 50000,
          "series_columns": [
            {
              "expressionType": "SQL",
              "label": "Sales Stage",
              "sqlExpression": "StageAggr"
            }
          ],
          "series_limit": 0,
          "order_desc": true,
          "url_params": {
            "slice_id": "429"
          },
          "custom_params": {},
          "custom_form_data": {},
          "time_offsets": [],
          "post_processing": [
            {
              "operation": "pivot",
              "options": {
                "index": ["SalesRep"],
                "columns": ["Sales Stage"],
                "aggregates": {
                  "Customer Count": {
                    "operator": "mean"
                  }
                },
                "drop_missing_columns": false
              }
            },
            {
              "operation": "rename",
              "options": {
                "columns": {
                  "Customer Count": null
                },
                "level": 0,
                "inplace": true
              }
            },
            {
              "operation": "flatten"
            }
          ]
        }
      ],
      "form_data": {
        "datasource": "54__table",
        "viz_type": "echarts_timeseries_bar",
        "slice_id": 429,
        "x_axis": "SalesRep",
        "metrics": [
          {
            "aggregate": "SUM",
            "column": {
              "column_name": "CustomerCount",
              "id": 789,
              "type": "UInt64"
            },
            "expressionType": "SIMPLE",
            "hasCustomLabel": true,
            "label": "Customer Count"
          }
        ],
        "groupby": [
          {
            "expressionType": "SQL",
            "label": "Sales Stage",
            "sqlExpression": "StageAggr"
          }
        ],
        "adhoc_filters": [
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "1=1",
            "subject": "CreatedOn",
            "operator": "TEMPORAL_RANGE",
            "operatorId": "TEMPORAL_RANGE"
          },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "StageAggr IS NOT NULL"
          },
          {
            "clause": "WHERE",
            "expressionType": "SIMPLE",
            "subject": "SalesRep",
            "operator": "NOT IN",
            "operatorId": "NOT_IN",
            "comparator": ["Test Sipay"]
          },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "StageAggr NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')"
          },
          {
            "clause": "WHERE",
            "expressionType": "SQL",
            "sqlExpression": "StageAggr IN ('7-Implementation', '9-Live', '8-Pilot')"
          }
        ],
        "order_desc": true,
        "row_limit": 50000
      },
      "result_format": "json",
      "result_type": "full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String respStr = response.body().string();
            return new JSONObject(respStr);
        }
    }

    public static JSONObject sendWidget51Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A500%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String whereClause =
                "(ActivationDate >= toStartOfMonth(today())) " +
                        "AND (SalesStage IS NOT NULL) " +
                        "AND (SalesStage NOT IN ('ContractRet','LeadRet','PitchedRet','Ret','ProspectRet')) " +
                        "AND (SalesRep IN ('Miraç Kayli','Bilal Kaptan','Yılmaz Güney','Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin','Nursah Sakin','Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek'))";

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]},
            {"col":"StageAggr","op":"IN","val":["7-Implementation","8-Pilot","9-Live"]}
          ],
          "extras":{"having":"","where":"%s"},
          "applied_time_extras":{},
          "columns":[],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"AssetId","id":384,"type":"INT"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count"
            }
          ],
          "annotation_layers":[],
          "series_limit":0,
          "order_desc":true,
          "row_limit":50000,
          "url_params":{"slice_id":"500"},
          "custom_params":{},
          "custom_form_data":{}
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"big_number_total",
        "slice_id":500,
        "metric":{
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"AssetId","id":384,"type":"INT"},
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count"
        },
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"ActivationDate >= toStartOfMonth(today())","subject":"CreatedOn","operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage IS NOT NULL"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"SalesRep","operator":"NOT IN","operatorId":"NOT_IN","comparator":["Test Sipay"]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('ContractRet','LeadRet','PitchedRet','Ret','ProspectRet')"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"StageAggr","operator":"IN","operatorId":"IN","comparator":["7-Implementation","8-Pilot","9-Live"]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesRep IN ('Miraç Kayli','Bilal Kaptan','Yılmaz Güney','Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin','Nursah Sakin','Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek')"}
        ],
        "y_axis_format":"SMART_NUMBER",
        "force":false,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """.formatted(whereClause.replace("\"", "\\\""));

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget55Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A499%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":58,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"UpdatedAt","op":"TEMPORAL_RANGE","val":"No filter"}
          ],
          "extras":{"having":"","where":""},
          "applied_time_extras":{},
          "columns":[
            {
              "expressionType":"SQL",
              "label":"Son Güncelleme",
              "sqlExpression":"UpdatedAt + INTERVAL 3 HOURS"
            }
          ],
          "orderby":[],
          "annotation_layers":[],
          "row_limit":1000,
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"499"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        }
      ],
      "form_data":{
        "datasource":"58__table",
        "viz_type":"table",
        "slice_id":499,
        "query_mode":"raw",
        "groupby":[],
        "temporal_columns_lookup":{"UpdatedAt":true},
        "metrics":[],
        "all_columns":[
          {
            "expressionType":"SQL",
            "label":"Son Güncelleme",
            "sqlExpression":"UpdatedAt + INTERVAL 3 HOURS"
          }
        ],
        "percent_metrics":[],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":"No filter",
            "expressionType":"SIMPLE",
            "operator":"TEMPORAL_RANGE",
            "subject":"UpdatedAt"
          }
        ],
        "order_by_cols":[],
        "row_limit":1000,
        "server_page_length":10,
        "order_desc":true,
        "table_timestamp_format":"%d-%m-%Y %H:%M:%S",
        "allow_render_html":true,
        "show_cell_bars":true,
        "color_pn":true,
        "comparison_color_scheme":"Green",
        "conditional_formatting":[],
        "comparison_type":"values",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full",
        "include_time":false
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget53Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A489%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]},
            {"col":"StageAggr","op":"IN","val":["3-Pitched"]},
            {"col":"StageDate","op":"TEMPORAL_RANGE","val":"No filter"}
          ],
          "extras":{
            "time_grain_sqla":"P1D",
            "having":"",
            "where":"(StageDate >= today()) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet','LeadRet','PitchedRet','Ret','ProspectRet')) AND (SalesRep IN ('Miraç Kayli','Bilal Kaptan','Yılmaz Güney','Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin','Nursah Sakin','Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek'))"
          },
          "applied_time_extras":{},
          "columns":[
            {"timeGrain":"P1D","columnType":"BASE_AXIS","sqlExpression":"StageDate","label":"StageDate","expressionType":"SQL"}
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"AssetId","id":384,"type":"INT"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"489"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[
            {"operation":"pivot","options":{"index":["StageDate"],"columns":[],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":true}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"big_number",
        "slice_id":489,
        "x_axis":"StageDate",
        "time_grain_sqla":"P1D",
        "metric":{
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"AssetId","id":384,"type":"INT"},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count",
          "sqlExpression":null
        },
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageDate >= today()","subject":"CreatedOn","operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage IS NOT NULL","subject":null},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"SalesRep","operator":"NOT IN","operatorId":"NOT_IN","comparator":["Test Sipay"]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('ContractRet','LeadRet','PitchedRet','Ret','ProspectRet')","subject":null},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"StageAggr","operator":"IN","operatorId":"IN","comparator":["3-Pitched"]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesRep IN ('Miraç Kayli','Bilal Kaptan','Yılmaz Güney','Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin','Nursah Sakin','Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek')","subject":null},
          {"clause":"WHERE","subject":"StageDate","operator":"TEMPORAL_RANGE","comparator":"No filter","expressionType":"SIMPLE"}
        ],
        "compare_lag":1,
        "compare_suffix":"DoD",
        "show_timestamp":true,
        "show_trend_line":true,
        "start_y_axis_at_zero":true,
        "header_font_size":0.4,
        "subheader_font_size":0.15,
        "y_axis_format":"SMART_NUMBER",
        "time_format":"smart_date",
        "rolling_type":"None",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }


    public static JSONObject sendWidget47Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A436%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":53,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]}
          ],
          "extras":{
            "having":"",
            "where":"(1=1) AND (StageAggr IS NOT NULL) AND (StageAggr NOT IN ('ContractRet','LeadRet','PitchedRet','Ret','ProspectRet')) AND (StageAggr IN ('7-Implementation','9-Live','8-Pilot'))"
          },
          "applied_time_extras":{},
          "columns":[
            {"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"},
            {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "metrics":[
            {
              "aggregate":"SUM",
              "column":{"column_name":"CustomerCount","id":786,"type":"UInt64"},
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count"
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"CustomerCount","id":786,"type":"UInt64"},
                "expressionType":"SIMPLE",
                "label":"Customer Count"
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_columns":[
            {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"436"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {
              "operation":"pivot",
              "options":{
                "index":["SalesRep"],
                "columns":["Sales Stage"],
                "aggregates":{"Customer Count":{"operator":"mean"}},
                "drop_missing_columns":false
              }
            },
            {
              "operation":"rename",
              "options":{
                "columns":{"Customer Count":null},
                "level":0,
                "inplace":true
              }
            },
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"53__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":436,
        "x_axis":"SalesRep",
        "x_axis_sort_asc":true,
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":false,
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"CustomerCount","id":786,"type":"UInt64"},
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count"
          }
        ],
        "groupby":[
          {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
        ],
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"1=1","subject":"CreatedOn","operator":"TEMPORAL_RANGE","operatorId":"TEMPORAL_RANGE"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageAggr IS NOT NULL"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"SalesRep","operator":"NOT IN","operatorId":"NOT_IN","comparator":["Test Sipay"]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageAggr NOT IN ('ContractRet','LeadRet','PitchedRet','Ret','ProspectRet')"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageAggr IN ('7-Implementation','9-Live','8-Pilot')"}
        ],
        "order_desc":true,
        "row_limit":50000,
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"horizontal",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"name",
        "sort_series_ascending":true,
        "color_scheme":"supersetColors",
        "show_value":true,
        "stack":"Stack",
        "only_total":true,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"bottom",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":0,
        "y_axis_format":"SMART_NUMBER",
        "logAxis":false,
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }


    public static JSONObject sendWidget56WeekRequest() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A158%7D&dashboard_id=8&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        // --- Dinamik tarih (ISO: Pazartesi haftanın ilk günü) ---
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(java.time.temporal.WeekFields.ISO.dayOfWeek(), 1); // Monday

        String fromDate = weekStart.format(df);
        String toDate = today.format(df);

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[],
          "extras":{
            "having":"",
            "where":"({%% if url_param('baslangic_tarih_to') %%}\\r\\n    StageDate <= toDate('{{ url_param('baslangic_tarih_to') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\nAND\\r\\n{%% if url_param('baslangic_tarih_from') %%}\\r\\n    StageDate >= toDate('{{ url_param('baslangic_tarih_from') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\n) AND (SalesStage NOT IN ('Live','Growth') AND SalesStage IS NOT NULL)"
          },
          "applied_time_extras":{},
          "columns":[
            {"columnType":"BASE_AXIS","datasourceWarning":false,"expressionType":"SQL","label":"SalesRep","sqlExpression":"RTRIM(SalesRep)"},
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"SalesStageAggr"}
          ],
          "metrics":[
            {
              "aggregate":"COUNT",
              "column":{"column_name":"SalesStage","id":386,"type":"Nullable(String)"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_cbtyr5qv2oh_x8hdztndtf8",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"COUNT",
                "column":{"column_name":"SalesStage","id":386,"type":"Nullable(String)"},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Customer Count",
                "optionName":"metric_cbtyr5qv2oh_x8hdztndtf8",
                "sqlExpression":null
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_columns":[
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"SalesStageAggr"}
          ],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {"operation":"pivot","options":{"index":["SalesRep"],"columns":["Sales Stage"],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},
            {"operation":"rename","options":{"columns":{"Customer Count":null},"level":0,"inplace":true}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":158,
        "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
        "x_axis":{"datasourceWarning":false,"expressionType":"SQL","label":"SalesRep","sqlExpression":"RTRIM(SalesRep)"},
        "x_axis_sort_asc":true,
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":false,
        "metrics":[
          {
            "aggregate":"COUNT",
            "column":{"column_name":"SalesStage","id":386,"type":"Nullable(String)"},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count",
            "optionName":"metric_cbtyr5qv2oh_x8hdztndtf8",
            "sqlExpression":null
          }
        ],
        "groupby":[
          {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"SalesStageAggr"}
        ],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "sqlExpression":"{%% if url_param('baslangic_tarih_to') %%}\\r\\n    StageDate <= toDate('{{ url_param('baslangic_tarih_to') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\nAND\\r\\n{%% if url_param('baslangic_tarih_from') %%}\\r\\n    StageDate >= toDate('{{ url_param('baslangic_tarih_from') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\n",
            "subject":null
          },
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"SalesStage NOT IN ('Live','Growth') AND SalesStage IS NOT NULL",
            "subject":"StageDate"
          }
        ],
        "order_desc":true,
        "row_limit":50000,
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"horizontal",
        "x_axis_title":"",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"name",
        "sort_series_ascending":true,
        "color_scheme":"supersetColors",
        "show_value":true,
        "stack":"Stack",
        "only_total":true,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"bottom",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":0,
        "y_axis_format":"SMART_NUMBER",
        "logAxis":false,
        "minorSplitLine":false,
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "dashboards":[8],
        "extra_form_data":{},
        "chart_id":158,
        "dashboardId":8,
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """.formatted(fromDate, toDate, fromDate, toDate);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget56MonthRequest() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A158%7D&dashboard_id=8&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        // --- Dinamik tarih (from: ayın ilk günü, to: bugün) ---
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);

        String fromDate = monthStart.format(df);
        String toDate = today.format(df);

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[],
          "extras":{
            "having":"",
            "where":"({%% if url_param('baslangic_tarih_to') %%}\\r\\n    StageDate <= toDate('{{ url_param('baslangic_tarih_to') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\nAND\\r\\n{%% if url_param('baslangic_tarih_from') %%}\\r\\n    StageDate >= toDate('{{ url_param('baslangic_tarih_from') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\n) AND (SalesStage NOT IN ('Live','Growth') AND SalesStage IS NOT NULL)"
          },
          "applied_time_extras":{},
          "columns":[
            {"columnType":"BASE_AXIS","datasourceWarning":false,"expressionType":"SQL","label":"SalesRep","sqlExpression":"RTRIM(SalesRep)"},
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"SalesStageAggr"}
          ],
          "metrics":[
            {
              "aggregate":"COUNT",
              "column":{"column_name":"SalesStage","id":386,"type":"Nullable(String)"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_cbtyr5qv2oh_x8hdztndtf8",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"COUNT",
                "column":{"column_name":"SalesStage","id":386,"type":"Nullable(String)"},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Customer Count",
                "optionName":"metric_cbtyr5qv2oh_x8hdztndtf8",
                "sqlExpression":null
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_columns":[
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"SalesStageAggr"}
          ],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {"operation":"pivot","options":{"index":["SalesRep"],"columns":["Sales Stage"],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},
            {"operation":"rename","options":{"columns":{"Customer Count":null},"level":0,"inplace":true}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":158,
        "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
        "x_axis":{"datasourceWarning":false,"expressionType":"SQL","label":"SalesRep","sqlExpression":"RTRIM(SalesRep)"},
        "x_axis_sort_asc":true,
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":false,
        "metrics":[
          {
            "aggregate":"COUNT",
            "column":{"column_name":"SalesStage","id":386,"type":"Nullable(String)"},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count",
            "optionName":"metric_cbtyr5qv2oh_x8hdztndtf8",
            "sqlExpression":null
          }
        ],
        "groupby":[
          {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"SalesStageAggr"}
        ],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "sqlExpression":"{%% if url_param('baslangic_tarih_to') %%}\\r\\n    StageDate <= toDate('{{ url_param('baslangic_tarih_to') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\nAND\\r\\n{%% if url_param('baslangic_tarih_from') %%}\\r\\n    StageDate >= toDate('{{ url_param('baslangic_tarih_from') }}')\\r\\n{%% else %%}\\r\\n    1=1\\r\\n{%% endif %%}\\r\\n",
            "subject":null
          },
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "sqlExpression":"SalesStage NOT IN ('Live','Growth') AND SalesStage IS NOT NULL",
            "subject":"StageDate"
          }
        ],
        "order_desc":true,
        "row_limit":50000,
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"horizontal",
        "x_axis_title":"",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"name",
        "sort_series_ascending":true,
        "color_scheme":"supersetColors",
        "show_value":true,
        "stack":"Stack",
        "only_total":true,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"bottom",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":0,
        "y_axis_format":"SMART_NUMBER",
        "logAxis":false,
        "minorSplitLine":false,
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "dashboards":[8],
        "extra_form_data":{},
        "chart_id":158,
        "dashboardId":8,
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """.formatted(fromDate, toDate, fromDate, toDate);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget52Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A487%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":35,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[],
          "extras":{
            "time_grain_sqla":"P1D",
            "having":"",
            "where":"(CreatedOn >= today() - 7) AND (SalesRep IN ('Miraç Kayli', 'Bilal Kaptan', 'Yılmaz Güney', 'Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin','Nursah Sakin','Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek'))"
          },
          "applied_time_extras":{},
          "columns":[
            {
              "timeGrain":"P1D",
              "columnType":"BASE_AXIS",
              "expressionType":"SQL",
              "label":"CreatedOn",
              "sqlExpression":"CreatedOn + INTERVAL 3 HOURS"
            }
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"deal_id","id":353,"type":"Int32"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_4lmp91ns7x_4qtxfbowwof",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"487"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[
            {
              "operation":"pivot",
              "options":{
                "index":["CreatedOn"],
                "columns":[],
                "aggregates":{"Customer Count":{"operator":"mean"}},
                "drop_missing_columns":true
              }
            },
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"35__table",
        "viz_type":"big_number",
        "slice_id":487,
        "x_axis":{"expressionType":"SQL","label":"CreatedOn","sqlExpression":"CreatedOn + INTERVAL 3 HOURS"},
        "time_grain_sqla":"P1D",
        "metric":{
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"deal_id","id":353,"type":"Int32"},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count",
          "optionName":"metric_4lmp91ns7x_4qtxfbowwof",
          "sqlExpression":null
        },
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "subject":"CreatedOn",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"CreatedOn >= today() - 7",
            "comparator":null,
            "datasourceWarning":false,
            "isExtra":false,
            "isNew":false,
            "filterOptionName":"filter_pe8t08a1hlo_d64xn8mjfl"
          },
          {
            "clause":"WHERE",
            "expressionType":"SQL",
            "sqlExpression":"SalesRep IN ('Miraç Kayli', 'Bilal Kaptan', 'Yılmaz Güney', 'Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin','Nursah Sakin','Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek')",
            "subject":null,
            "comparator":null,
            "datasourceWarning":false,
            "isExtra":false,
            "isNew":false,
            "operator":null,
            "filterOptionName":"filter_53dt5i1gskj_3sfxj6zkwr2"
          }
        ],
        "compare_lag":1,
        "compare_suffix":"DoD",
        "show_timestamp":true,
        "show_trend_line":true,
        "start_y_axis_at_zero":true,
        "color_picker":{"a":1,"b":135,"g":122,"r":0},
        "header_font_size":0.4,
        "subheader_font_size":0.15,
        "y_axis_format":"SMART_NUMBER",
        "time_format":"smart_date",
        "rolling_type":"None",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }


    public static JSONObject sendWidget57Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A122%7D&dashboard_id=7&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        // Bugün (yyyy-MM-dd)
        java.time.LocalDate today = java.time.LocalDate.now();
        String todayStr = today.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);

        String body = """
    {
      "datasource":{"id":32,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"CreatedOn","op":"TEMPORAL_RANGE","val":"No filter"},
            {"col":"AssociationDate","op":"TEMPORAL_RANGE","val":"%s : %s"}
          ],
          "extras":{"having":"","where":""},
          "applied_time_extras":{},
          "columns":[
            "DealId",
            "SKU",
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sipay ID","sqlExpression":"Sipay_Panel_ID"},
            "CreatedOn",
            "ASSIGNED_SALES_REPRESANTATIVE_DEAL",
            "Assignee",
            "AssociationDate",
            "2_1024",
            "EMAIL",
            {"datasourceWarning":false,"expressionType":"SQL","label":"Company","sqlExpression":"Account_Name_Deal"},
            "DIA_FirstName",
            "DIA_LastName",
            "MOBILE_PHONE",
            "1_1026",
            "1_1056",
            "1_1127",
            "1_32",
            "ebMobile__Vendor__c",
            "BELGE_TARIHI",
            "Description",
            {"expressionType":"SQL","label":"Monthly POS Volume (TRY)","sqlExpression":"ESTIMATED_VOLUME"},
            {"expressionType":"SQL","label":"Monthly POS Volume (USD)","sqlExpression":"ESTIMATED_VOLUME_USD"},
            {"expressionType":"SQL","label":"Yearly POS Volume (TRY)","sqlExpression":"DISTILE_VADEGUN_TRY"},
            {"expressionType":"SQL","label":"Yearly POS Volume (USD)","sqlExpression":"DISTILE_VADEGUN_USD"},
            {"expressionType":"SQL","label":"Est. Annual Rev. (TRY)","sqlExpression":"PREMRAKI_VADEGUN"},
            {"expressionType":"SQL","label":"Est. Annual Rev. (USD)","sqlExpression":"SARAP_VADEGUN"},
            "PAYLASIM_TIPI",
            "Prospect_Durum",
            "Referrer",
            "SEGMENT",
            "SIPAY_STATUS_FILE",
            "SUB_INDUSTRIES",
            "1_24",
            "AccountExecutiveCode__c",
            "ASM_Name__c",
            "AUTHORIZED_PERSON",
            "AUTHORIZED_PERSON_ID",
            "Business_Type_Extent__c",
            "COMPANY_TYPE",
            "DIA_A_VN",
            "Sipay_Panel_ID",
            "Sipay_Wallet_Master_ID",
            {"datasourceWarning":false,"expressionType":"SQL","label":"Website","sqlExpression":"Website_Deal"},
            "1_1060123"
          ],
          "orderby":[],
          "annotation_layers":[],
          "row_limit":50000,
          "series_limit":0,
          "order_desc":true,
          "url_params":{"uiConfig":"0"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[],
          "time_offsets":[]
        }
      ],
      "form_data":{
        "datasource":"32__table",
        "viz_type":"table",
        "slice_id":122,
        "url_params":{"uiConfig":"0"},
        "query_mode":"raw",
        "groupby":[],
        "temporal_columns_lookup":{"AssociationDate":true,"CreatedOn":true},
        "all_columns":[
          "DealId",
          "SKU",
          {"datasourceWarning":false,"expressionType":"SQL","label":"Sipay ID","sqlExpression":"Sipay_Panel_ID"},
          "CreatedOn",
          "ASSIGNED_SALES_REPRESANTATIVE_DEAL",
          "Assignee",
          "AssociationDate",
          "2_1024",
          "EMAIL",
          {"datasourceWarning":false,"expressionType":"SQL","label":"Company","sqlExpression":"Account_Name_Deal"},
          "DIA_FirstName",
          "DIA_LastName",
          "MOBILE_PHONE",
          "1_1026",
          "1_1056",
          "1_1127",
          "1_32",
          "ebMobile__Vendor__c",
          "BELGE_TARIHI",
          "Description",
          {"expressionType":"SQL","label":"Monthly POS Volume (TRY)","sqlExpression":"ESTIMATED_VOLUME"},
          {"expressionType":"SQL","label":"Monthly POS Volume (USD)","sqlExpression":"ESTIMATED_VOLUME_USD"},
          {"expressionType":"SQL","label":"Yearly POS Volume (TRY)","sqlExpression":"DISTILE_VADEGUN_TRY"},
          {"expressionType":"SQL","label":"Yearly POS Volume (USD)","sqlExpression":"DISTILE_VADEGUN_USD"},
          {"expressionType":"SQL","label":"Est. Annual Rev. (TRY)","sqlExpression":"PREMRAKI_VADEGUN"},
          {"expressionType":"SQL","label":"Est. Annual Rev. (USD)","sqlExpression":"SARAP_VADEGUN"},
          "PAYLASIM_TIPI",
          "Prospect_Durum",
          "Referrer",
          "SEGMENT",
          "SIPAY_STATUS_FILE",
          "SUB_INDUSTRIES",
          "1_24",
          "AccountExecutiveCode__c",
          "ASM_Name__c",
          "AUTHORIZED_PERSON",
          "AUTHORIZED_PERSON_ID",
          "Business_Type_Extent__c",
          "COMPANY_TYPE",
          "DIA_A_VN",
          "Sipay_Panel_ID",
          "Sipay_Wallet_Master_ID",
          {"datasourceWarning":false,"expressionType":"SQL","label":"Website","sqlExpression":"Website_Deal"},
          "1_1060123"
        ],
        "percent_metrics":[],
        "adhoc_filters":[
          {"clause":"WHERE","comparator":"No filter","expressionType":"SIMPLE","operator":"TEMPORAL_RANGE","subject":"CreatedOn"},
          {"clause":"WHERE","comparator":"%s : %s","expressionType":"SIMPLE","operator":"TEMPORAL_RANGE","subject":"AssociationDate"}
        ],
        "order_by_cols":[],
        "row_limit":50000,
        "server_page_length":10,
        "order_desc":true,
        "table_timestamp_format":"smart_date",
        "include_search":true,
        "allow_render_html":true,
        "show_cell_bars":false,
        "color_pn":true,
        "comparison_color_scheme":"Green",
        "conditional_formatting":[],
        "comparison_type":"values",
        "dashboards":[7],
        "extra_form_data":{},
        "chart_id":122,
        "extra_filters":[],
        "dashboardId":7,
        "force":true,
        "result_format":"json",
        "result_type":"full",
        "include_time":false
      },
      "result_format":"json",
      "result_type":"full"
    }
    """.formatted(todayStr, todayStr, todayStr, todayStr);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget45Request() throws IOException {
        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A431%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":52,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]}
          ],
          "extras":{
            "having":"",
            "where":"(1=1) AND (StageAggr NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (StageAggr IN ('7-Implementation', '9-Live', '8-Pilot'))"
          },
          "applied_time_extras":{},
          "columns":[
            {"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"},
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "metrics":[
            {
              "aggregate":"SUM",
              "column":{"column_name":"CustomerCount","id":783,"type":"UInt64"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_tk3ep2tmkdj_6nhx6tobd4q",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"CustomerCount","id":783,"type":"UInt64"},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Customer Count",
                "optionName":"metric_tk3ep2tmkdj_6nhx6tobd4q",
                "sqlExpression":null
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_columns":[
            {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"431"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {
              "operation":"pivot",
              "options":{
                "index":["SalesRep"],
                "columns":["Sales Stage"],
                "aggregates":{"Customer Count":{"operator":"mean"}},
                "drop_missing_columns":false
              }
            },
            {
              "operation":"rename",
              "options":{"columns":{"Customer Count":null},"level":0,"inplace":true}
            },
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"52__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":431,
        "url_params":{"slice_id":"431"},
        "x_axis":"SalesRep",
        "x_axis_sort_asc":true,
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":false,
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"CustomerCount","id":783,"type":"UInt64"},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count",
            "optionName":"metric_tk3ep2tmkdj_6nhx6tobd4q",
            "sqlExpression":null
          }
        ],
        "groupby":[
          {"datasourceWarning":false,"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
        ],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"1=1",
            "subject":"CreatedOn"
          },
          {
            "clause":"WHERE",
            "comparator":["Test Sipay"],
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "operator":"NOT IN",
            "operatorId":"NOT_IN",
            "sqlExpression":null,
            "subject":"SalesRep"
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "sqlExpression":"StageAggr NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')",
            "subject":null
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "sqlExpression":"StageAggr IN ('7-Implementation', '9-Live', '8-Pilot')",
            "subject":null
          }
        ],
        "order_desc":true,
        "row_limit":50000,
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"horizontal",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"name",
        "sort_series_ascending":true,
        "color_scheme":"supersetColors",
        "show_value":true,
        "stack":"Stack",
        "only_total":true,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"bottom",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":0,
        "y_axis_format":"SMART_NUMBER",
        "logAxis":false,
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }


    public static JSONObject sendWidget54Request() throws IOException {
        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A490%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]},
            {"col":"StageAggr","op":"IN","val":["7-Implementation","8-Pilot","9-Live"]},
            {"col":"ActivationDate","op":"TEMPORAL_RANGE","val":"No filter"}
          ],
          "extras":{
            "time_grain_sqla":"P1D",
            "having":"",
            "where":"(ActivationDate >= today() - 7) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')) AND (SalesRep IN ('Miraç Kayli', 'Bilal Kaptan', 'Yılmaz Güney', 'Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin', 'Nursah Sakin', 'Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek'))"
          },
          "applied_time_extras":{},
          "columns":[
            {"timeGrain":"P1D","columnType":"BASE_AXIS","sqlExpression":"ActivationDate","label":"ActivationDate","expressionType":"SQL"}
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"AssetId","id":384,"type":"INT"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_w67h0a7x6fk_2zllxkz1jgr",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"490"},
          "custom_params":{},
          "custom_form_data":{},
          "post_processing":[
            {"operation":"pivot","options":{"index":["ActivationDate"],"columns":[],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":true}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"big_number",
        "slice_id":490,
        "url_params":{"slice_id":"490"},
        "x_axis":"ActivationDate",
        "time_grain_sqla":"P1D",
        "metric":{
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"AssetId","id":384,"type":"INT"},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count",
          "optionName":"metric_w67h0a7x6fk_2zllxkz1jgr",
          "sqlExpression":null
        },
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"ActivationDate >= today() - 7",
            "subject":"CreatedOn"
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "sqlExpression":"SalesStage IS NOT NULL",
            "subject":null
          },
          {
            "clause":"WHERE",
            "comparator":["Test Sipay"],
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "operator":"NOT IN",
            "operatorId":"NOT_IN",
            "sqlExpression":null,
            "subject":"SalesRep"
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "sqlExpression":"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')",
            "subject":null
          },
          {
            "clause":"WHERE",
            "comparator":["7-Implementation","8-Pilot","9-Live"],
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "operator":"IN",
            "operatorId":"IN",
            "sqlExpression":null,
            "subject":"StageAggr"
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "sqlExpression":"SalesRep IN ('Miraç Kayli', 'Bilal Kaptan', 'Yılmaz Güney', 'Cem Özçitakgil','Merve Akan','Arif Özsoy','Eren Gedik','Murat Yengi','Ender Kolistoğlu','Cemal Kutlu','Sena Yıldırım','Merve Ulusoy','Emre Uzun','Can Demirkıran','Nurşah Sakin', 'Nursah Sakin', 'Melih Topçu','Korhan Başar','Alperen Bağışlar','Hilmi Kara','Melis Arslan','Furkan Kızılkurt','Beytullah Sayılır','Okan Ergün','Hamza Turan','Hakan Çıkmaz','Arif Özsoy','Arif Ozsoy','Beytullah Sayılır','Can Demirkıran','Cem Ozcitakgil','Cemal Kutlu','Emre Uzun','Ender Kolistoğlu','Eren Gedik','Fırat Karateke','Firat Karateke','Hakan Cıkmaz','Hakan Çıkmaz','Hakan Cikmaz','Hamza Turan','Kaan Güzel','Kaan Guzel','Melih Topçu','Melih Topcu','Merve Akan','Merve Ulusoy','Miraç Kaylı','Murat Yengi','Mustafa Yayla','Nurşah Sakin','Nursah Sakin','Okan Ergün','Okan Ergun','Sena Yıldırım','Seyfullah Günay','Yeşim Yeşbek')",
            "subject":null
          },
          {
            "clause":"WHERE",
            "subject":"ActivationDate",
            "operator":"TEMPORAL_RANGE",
            "comparator":"No filter",
            "expressionType":"SIMPLE"
          }
        ],
        "compare_lag":1,
        "compare_suffix":"DoD",
        "show_timestamp":true,
        "show_trend_line":true,
        "start_y_axis_at_zero":true,
        "header_font_size":0.4,
        "subheader_font_size":0.15,
        "y_axis_format":"SMART_NUMBER",
        "time_format":"smart_date",
        "rolling_type":"None",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }


    public static JSONObject sendWidget46Request() throws IOException {
        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A496%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":57,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]}
          ],
          "extras":{"having":"","where":"(1=1)"},
          "applied_time_extras":{},
          "columns":[
            {"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"}
          ],
          "metrics":[
            {
              "aggregate":"SUM",
              "column":{"column_name":"CustomerCount","id":795,"type":"UInt64"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_o25wf76ho8s_2tlcqs9vim1",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"CustomerCount","id":795,"type":"UInt64"},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Customer Count",
                "optionName":"metric_o25wf76ho8s_2tlcqs9vim1",
                "sqlExpression":null
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_columns":[],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"496"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {"operation":"pivot","options":{"index":["SalesRep"],"columns":[],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},
            {"operation":"sort","options":{"is_sort_index":true,"ascending":false}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"57__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":496,
        "url_params":{"slice_id":"496"},
        "x_axis":"SalesRep",
        "xAxisForceCategorical":true,
        "x_axis_sort":"SalesRep",
        "x_axis_sort_asc":false,
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":true,
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"CustomerCount","id":795,"type":"UInt64"},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count",
            "optionName":"metric_o25wf76ho8s_2tlcqs9vim1",
            "sqlExpression":null
          }
        ],
        "groupby":[],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"1=1",
            "subject":"CreatedOn"
          },
          {
            "clause":"WHERE",
            "comparator":["Test Sipay"],
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "operator":"NOT IN",
            "operatorId":"NOT_IN",
            "sqlExpression":null,
            "subject":"SalesRep"
          }
        ],
        "order_desc":true,
        "row_limit":50000,
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"horizontal",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"name",
        "sort_series_ascending":true,
        "color_scheme":"supersetColors",
        "show_value":true,
        "stack":"Stack",
        "only_total":true,
        "show_legend":false,
        "legendType":"scroll",
        "legendOrientation":"top",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":0,
        "y_axis_format":"SMART_NUMBER",
        "logAxis":false,
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }


    public static JSONObject sendWidget48Request() throws IOException {
        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A498%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        String body = """
    {
      "datasource":{"id":55,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[
            {"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]}
          ],
          "extras":{"having":"","where":"(1=1)"},
          "applied_time_extras":{},
          "columns":[
            {"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"}
          ],
          "metrics":[
            {
              "aggregate":"SUM",
              "column":{"column_name":"CustomerCount","id":791,"type":"UInt64"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_lkkemd1h04a_bmumtem3cxh",
              "sqlExpression":null
            }
          ],
          "orderby":[
            [
              {
                "aggregate":"SUM",
                "column":{"column_name":"CustomerCount","id":791,"type":"UInt64"},
                "datasourceWarning":false,
                "expressionType":"SIMPLE",
                "hasCustomLabel":true,
                "label":"Customer Count",
                "optionName":"metric_lkkemd1h04a_bmumtem3cxh",
                "sqlExpression":null
              },
              false
            ]
          ],
          "annotation_layers":[],
          "row_limit":50000,
          "series_columns":[],
          "series_limit":0,
          "order_desc":true,
          "url_params":{"slice_id":"498"},
          "custom_params":{},
          "custom_form_data":{},
          "time_offsets":[],
          "post_processing":[
            {"operation":"pivot","options":{"index":["SalesRep"],"columns":[],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},
            {"operation":"sort","options":{"is_sort_index":true,"ascending":false}},
            {"operation":"flatten"}
          ]
        }
      ],
      "form_data":{
        "datasource":"55__table",
        "viz_type":"echarts_timeseries_bar",
        "slice_id":498,
        "url_params":{"slice_id":"498"},
        "x_axis":"SalesRep",
        "xAxisForceCategorical":true,
        "x_axis_sort":"SalesRep",
        "x_axis_sort_asc":false,
        "x_axis_sort_series":"name",
        "x_axis_sort_series_ascending":true,
        "metrics":[
          {
            "aggregate":"SUM",
            "column":{"column_name":"CustomerCount","id":791,"type":"UInt64"},
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "hasCustomLabel":true,
            "label":"Customer Count",
            "optionName":"metric_lkkemd1h04a_bmumtem3cxh",
            "sqlExpression":null
          }
        ],
        "groupby":[],
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"1=1",
            "subject":"CreatedOn"
          },
          {
            "clause":"WHERE",
            "comparator":["Test Sipay"],
            "datasourceWarning":false,
            "expressionType":"SIMPLE",
            "operator":"NOT IN",
            "operatorId":"NOT_IN",
            "sqlExpression":null,
            "subject":"SalesRep"
          }
        ],
        "order_desc":true,
        "row_limit":50000,
        "truncate_metric":true,
        "show_empty_columns":true,
        "comparison_type":"values",
        "annotation_layers":[],
        "forecastPeriods":10,
        "forecastInterval":0.8,
        "orientation":"horizontal",
        "x_axis_title_margin":15,
        "y_axis_title_margin":15,
        "y_axis_title_position":"Left",
        "sort_series_type":"name",
        "sort_series_ascending":true,
        "color_scheme":"supersetColors",
        "show_value":true,
        "stack":"Stack",
        "only_total":true,
        "show_legend":false,
        "legendType":"scroll",
        "legendOrientation":"top",
        "x_axis_time_format":"smart_date",
        "xAxisLabelRotation":0,
        "y_axis_format":"SMART_NUMBER",
        "logAxis":false,
        "truncateXAxis":true,
        "y_axis_bounds":[null,null],
        "rich_tooltip":true,
        "showTooltipTotal":true,
        "showTooltipPercentage":true,
        "tooltipTimeFormat":"smart_date",
        "extra_form_data":{},
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """;

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget1WeeklyRequest() throws IOException {

        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A180%7D&dashboard_id=8&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        // --- Dinamik tarih: haftanın ilk günü (Pazartesi) -> bugün ---
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(java.time.temporal.WeekFields.ISO.dayOfWeek(), 1);

        String fromDate = weekStart.format(df);
        String toDate = today.format(df);

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[],
          "extras":{
            "having":"",
            "where":"(StageDate >= toStartOfWeek(today())) AND (SalesStage NOT IN ('Live', 'Growth')) AND (SalesStage IS NOT NULL) AND (SalesRep NOT IN ('Test Sipay'))"
          },
          "applied_time_extras":{},
          "columns":[
            {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"AssetId","id":384,"type":"INT"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_8l94erlcc36_d1kvs1t4eza",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "row_limit":100,
          "series_limit":0,
          "order_desc":true,
          "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
          "custom_params":{},
          "custom_form_data":{}
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"pie",
        "slice_id":180,
        "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
        "groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],
        "metric":{
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"AssetId","id":384,"type":"INT"},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count",
          "optionName":"metric_8l94erlcc36_d1kvs1t4eza",
          "sqlExpression":null
        },
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"StageDate >= toStartOfWeek(today())",
            "subject":"CreatedOn"
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":null,
            "sqlExpression":"SalesStage NOT IN ('Live', 'Growth')",
            "subject":null
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":null,
            "sqlExpression":"SalesStage IS NOT NULL",
            "subject":null
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":null,
            "sqlExpression":"SalesRep NOT IN ('Test Sipay')",
            "subject":null
          }
        ],
        "row_limit":100,
        "sort_by_metric":false,
        "color_scheme":"supersetColors",
        "show_labels_threshold":5,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"left",
        "label_type":"value_percent",
        "number_format":"SMART_NUMBER",
        "date_format":"smart_date",
        "show_labels":true,
        "labels_outside":true,
        "label_line":true,
        "show_total":false,
        "outerRadius":72,
        "donut":true,
        "innerRadius":46,
        "dashboards":[8],
        "extra_form_data":{},
        "chart_id":180,
        "dashboardId":8,
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """.formatted(fromDate, toDate, fromDate, toDate);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }

    public static JSONObject sendWidget1MonthlyRequest() throws IOException {

        final String url =
                "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A234%7D&dashboard_id=8&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        String cookie = ConfigurationReader.getProperty("cookie");

        // --- Dinamik tarih: ayın başı -> bugün ---
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);

        String fromDate = monthStart.format(df);
        String toDate = today.format(df);

        String body = """
    {
      "datasource":{"id":36,"type":"table"},
      "force":true,
      "queries":[
        {
          "filters":[],
          "extras":{
            "having":"",
            "where":"(StageDate >= toStartOfMonth(today())) AND (SalesStage NOT IN ('Live', 'Growth')) AND (SalesStage IS NOT NULL)"
          },
          "applied_time_extras":{},
          "columns":[
            {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
          ],
          "metrics":[
            {
              "aggregate":"COUNT_DISTINCT",
              "column":{"column_name":"AssetId","id":384,"type":"INT"},
              "datasourceWarning":false,
              "expressionType":"SIMPLE",
              "hasCustomLabel":true,
              "label":"Customer Count",
              "optionName":"metric_8l94erlcc36_d1kvs1t4eza",
              "sqlExpression":null
            }
          ],
          "annotation_layers":[],
          "row_limit":100,
          "series_limit":0,
          "order_desc":true,
          "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
          "custom_params":{},
          "custom_form_data":{}
        }
      ],
      "form_data":{
        "datasource":"36__table",
        "viz_type":"pie",
        "slice_id":234,
        "url_params":{"baslangic_tarih_from":"%s","baslangic_tarih_to":"%s","uiConfig":"0"},
        "groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],
        "metric":{
          "aggregate":"COUNT_DISTINCT",
          "column":{"column_name":"AssetId","id":384,"type":"INT"},
          "datasourceWarning":false,
          "expressionType":"SIMPLE",
          "hasCustomLabel":true,
          "label":"Customer Count",
          "optionName":"metric_8l94erlcc36_d1kvs1t4eza",
          "sqlExpression":null
        },
        "adhoc_filters":[
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":"TEMPORAL_RANGE",
            "operatorId":"TEMPORAL_RANGE",
            "sqlExpression":"StageDate >= toStartOfMonth(today())",
            "subject":"CreatedOn"
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":null,
            "sqlExpression":"SalesStage NOT IN ('Live', 'Growth')",
            "subject":null
          },
          {
            "clause":"WHERE",
            "comparator":null,
            "datasourceWarning":false,
            "expressionType":"SQL",
            "operator":null,
            "sqlExpression":"SalesStage IS NOT NULL",
            "subject":null
          }
        ],
        "row_limit":100,
        "sort_by_metric":false,
        "color_scheme":"supersetColors",
        "show_labels_threshold":5,
        "show_legend":true,
        "legendType":"scroll",
        "legendOrientation":"left",
        "label_type":"value_percent",
        "number_format":"SMART_NUMBER",
        "date_format":"smart_date",
        "show_labels":true,
        "labels_outside":true,
        "label_line":true,
        "show_total":false,
        "outerRadius":72,
        "donut":true,
        "innerRadius":46,
        "dashboards":[8],
        "extra_form_data":{},
        "chart_id":234,
        "dashboardId":8,
        "force":true,
        "result_format":"json",
        "result_type":"full"
      },
      "result_format":"json",
      "result_type":"full"
    }
    """.formatted(fromDate, toDate, fromDate, toDate);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Cookie", "session=" + cookie)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = (response.body() != null) ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + "\nResponse body: " + resp);
            }
            return new JSONObject(resp);
        }
    }







}
