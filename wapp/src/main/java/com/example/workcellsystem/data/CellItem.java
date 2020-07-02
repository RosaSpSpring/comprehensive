package com.example.workcellsystem.data;

public class CellItem {
    private String cellName;
    private String projectName;
    private String number;
    private String startDate;
    private String endDate;

    public CellItem(String cellName, String projectName, String number, String startDate, String endDate) {
        this.cellName = cellName;
        this.projectName = projectName;
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
