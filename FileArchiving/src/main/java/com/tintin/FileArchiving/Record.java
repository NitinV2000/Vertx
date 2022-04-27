package com.tintin.FileArchiving;

public class Record {
    private String id;
    private int archievalInterval;
    private String storageType;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getArchievalInterval() {
        return archievalInterval;
    }
    public void setArchievalInterval(int archievalInterval) {
        this.archievalInterval = archievalInterval;
    }
    public String getStorageType() {
        return storageType;
    }
    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
    @Override
    public String toString() {
        return "Record [archievalInterval=" + archievalInterval + ", id=" + id + ", storageType=" + storageType + "]";
    }
    public Record(String id, int archievalInterval, String storageType) {
        this.id = id;
        this.archievalInterval = archievalInterval;
        this.storageType = storageType;
    }
    public Record() {
    }   
}
