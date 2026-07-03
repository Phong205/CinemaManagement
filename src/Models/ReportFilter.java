package Models;

public class ReportFilter {
    private String reportType;
    private String startDate;
    private String endDate;

    public ReportFilter(String reportType, String startDate, String endDate) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isValid() {
        return reportType != null && !reportType.trim().isEmpty();
    }

    public String getReportType() {
        return reportType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}