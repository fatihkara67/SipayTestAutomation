package Efectura.utilities;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class Requests {

    static int timeoutValue = Integer.parseInt(ConfigurationReader.getProperty("timeout"));

    public static JSONObject sendWidget36Request() {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A433%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
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

    public static JSONObject sendWidget3Request() throws IOException {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A222%7D&force=true";

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
      "datasource": {"id":36,"type":"table"},
      "force": true,
      "queries": [{
        "filters":[{"col":"SalesRep","op":"NOT IN","val":["Test Sipay"]}],
        "extras":{
          "having":"",
          "where":"(StageDate >= toStartOfWeek(today())) AND (SalesStage IS NOT NULL) AND (SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet'))"
        },
        "applied_time_extras":{},
        "columns":[
          {"columnType":"BASE_AXIS","sqlExpression":"SalesRep","label":"SalesRep","expressionType":"SQL"},
          {"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}
        ],
        "metrics":[{"aggregate":"COUNT_DISTINCT","column":{"column_name":"AssetId","id":384,"type":"INT"},"expressionType":"SIMPLE","label":"Customer Count"}],
        "orderby":[[{"aggregate":"COUNT_DISTINCT","column":{"column_name":"AssetId","id":384,"type":"INT"},"expressionType":"SIMPLE","label":"Customer Count"},false]],
        "annotation_layers":[],
        "row_limit":50000,
        "series_columns":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],
        "series_limit":0,
        "order_desc":true,
        "url_params":{"slice_id":"222"},
        "custom_params":{},
        "custom_form_data":{},
        "time_offsets":[],
        "post_processing":[
          {"operation":"pivot","options":{"index":["SalesRep"],"columns":["Sales Stage"],"aggregates":{"Customer Count":{"operator":"mean"}},"drop_missing_columns":false}},
          {"operation":"rename","options":{"columns":{"Customer Count":null},"level":0,"inplace":true}},
          {"operation":"flatten"}
        ]
      }],
      "form_data":{
        "datasource":"36__table","viz_type":"echarts_timeseries_bar","slice_id":222,
        "groupby":[{"expressionType":"SQL","label":"Sales Stage","sqlExpression":"StageAggr"}],
        "metrics":[{"aggregate":"COUNT_DISTINCT","column":{"column_name":"AssetId","id":384,"type":"INT"},"expressionType":"SIMPLE","label":"Customer Count"}],
        "adhoc_filters":[
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"StageDate >= toStartOfWeek(today())"},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage IS NOT NULL"},
          {"clause":"WHERE","expressionType":"SIMPLE","subject":"SalesRep","operator":"NOT_IN","comparator":["Test Sipay"]},
          {"clause":"WHERE","expressionType":"SQL","sqlExpression":"SalesStage NOT IN ('ContractRet', 'LeadRet', 'PitchedRet', 'Ret', 'ProspectRet')"}
        ],
        "row_limit":50000
      },
      "result_format":"json","result_type":"full"
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

    public static JSONObject sendWidget40Request() {
        final String url = "https://crm-dashboard.spwgpf.com/api/v1/chart/data?form_data=%7B%22slice_id%22%3A436%7D&force=true";

        OkHttpClient client = InsecureHttp.newClient()
                .newBuilder()
                .connectTimeout(timeoutValue, TimeUnit.SECONDS)
                .readTimeout(timeoutValue, TimeUnit.SECONDS)
                .writeTimeout(timeoutValue, TimeUnit.SECONDS)
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




}
