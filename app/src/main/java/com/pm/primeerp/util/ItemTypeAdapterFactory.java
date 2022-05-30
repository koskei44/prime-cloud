package com.pm.primeerp.util;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

public class ItemTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
//                    if (jsonObject.has("data")) {
//                        jsonElement = jsonObject.get("data");
//                    }
                    if (jsonObject.has("agent")) {
                        jsonElement = jsonObject.get("agent");
                    }
                    if (jsonObject.has("company")) {
                        jsonElement = jsonObject.get("company");
                    }
                    if (jsonObject.has("consignee")) {
                        jsonElement = jsonObject.get("consignee");
                    }
                    if (jsonObject.has("conveyanceMeans")) {
                        jsonElement = jsonObject.get("conveyanceMeans");
                    }
                    if (jsonObject.has("country")) {
                        jsonElement = jsonObject.get("country");
                    }
                    if (jsonObject.has("county")) {
                        jsonElement = jsonObject.get("county");
                    }
                    if (jsonObject.has("department")) {
                        jsonElement = jsonObject.get("department");
                    }
                    if (jsonObject.has("docStatus")) {
                        jsonElement = jsonObject.get("docStatus");
                    }
                    if (jsonObject.has("entityType")) {
                        jsonElement = jsonObject.get("entityType");
                    }
                    if (jsonObject.has("exporter")) {
                        jsonElement = jsonObject.get("exporter");
                    }
                    if (jsonObject.has("exporterConsignment")) {
                        jsonElement = jsonObject.get("exporterConsignment");
                    }
                    if (jsonObject.has("exporterFarm")) {
                        jsonElement = jsonObject.get("exporterFarm");
                    }
                    if (jsonObject.has("exporterFirm")) {
                        jsonElement = jsonObject.get("exporterFirm");
                    }
                    if (jsonObject.has("exporterInspectionRequest")) {
                        jsonElement = jsonObject.get("exporterInspectionRequest");
                    }
                    if (jsonObject.has("exporterLowRiskCommodity")) {
                        jsonElement = jsonObject.get("exporterLowRiskCommodity");
                    }
                    if (jsonObject.has("exporterType")) {
                        jsonElement = jsonObject.get("exporterType");
                    }
                    if (jsonObject.has("importer")) {
                        jsonElement = jsonObject.get("importer");
                    }
                    if (jsonObject.has("importerExporter")) {
                        jsonElement = jsonObject.get("importerExporter");
                    }
                    if (jsonObject.has("importerType")) {
                        jsonElement = jsonObject.get("importerType");
                    }
                    if (jsonObject.has("importPurpose")) {
                        jsonElement = jsonObject.get("importPurpose");
                    }
                    if (jsonObject.has("inspectionHeader")) {
                        jsonElement = jsonObject.get("inspectionHeader");
                    }
                    if (jsonObject.has("inspectionLine")) {
                        jsonElement = jsonObject.get("inspectionLine");
                    }
                    if (jsonObject.has("inspectionLocation")) {
                        jsonElement = jsonObject.get("inspectionLocation");
                    }
                    if (jsonObject.has("inspectionRequirement")) {
                        jsonElement = jsonObject.get("inspectionRequirement");
                    }
                    if (jsonObject.has("inspectionType")) {
                        jsonElement = jsonObject.get("inspectionType");
                    }
                    if (jsonObject.has("licensingBody")) {
                        jsonElement = jsonObject.get("licensingBody");
                    }
                    if (jsonObject.has("pipApplication")) {
                        jsonElement = jsonObject.get("pipApplication");
                    }
                    if (jsonObject.has("productCategory")) {
                        jsonElement = jsonObject.get("productCategory");
                    }
                    if (jsonObject.has("productClass")) {
                        jsonElement = jsonObject.get("productClass");
                    }
                    if (jsonObject.has("productForm")) {
                        jsonElement = jsonObject.get("productForm");
                    }
                    if (jsonObject.has("products")) {
                        jsonElement = jsonObject.get("products");
                    }
                    if (jsonObject.has("productVariety")) {
                        jsonElement = jsonObject.get("productVariety");
                    }
                    if (jsonObject.has("quarantineFacility")) {
                        jsonElement = jsonObject.get("quarantineFacility");
                    }
                    if (jsonObject.has("researcherCategory")) {
                        jsonElement = jsonObject.get("researcherCategory");
                    }
                    if (jsonObject.has("researchInstitute")) {
                        jsonElement = jsonObject.get("researchInstitute");
                    }
                    if (jsonObject.has("role")) {
                        jsonElement = jsonObject.get("role");
                    }
                    if (jsonObject.has("user")) {
                        jsonElement = jsonObject.get("user");
                    }
                    if (jsonObject.has("siUnit")) {
                        jsonElement = jsonObject.get("siUnit");
                    }
                    if (jsonObject.has("subCounty")) {
                        jsonElement = jsonObject.get("subCounty");
                    }
                    if (jsonObject.has("tradingBlock")) {
                        jsonElement = jsonObject.get("tradingBlock");
                    }
                    if (jsonObject.has("ward")) {
                        jsonElement = jsonObject.get("ward");
                    }
                }

                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
