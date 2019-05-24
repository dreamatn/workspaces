package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZTRID {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICTRID CICTRID;
    public void MP(SCCGWA SCCGWA, CICTRID CICTRID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICTRID = CICTRID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZTRID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICTRID.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_TRANS_OLD_ID_TO_NEW();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICTRID.DATA.OLD_IDNO);
        if (CICTRID.DATA.OLD_IDNO.trim().length() == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANS_OLD_ID_TO_NEW() throws IOException,SQLException,Exception {
        CICTRID.DATA.NEW_IDTYP = " ";
        CICTRID.DATA.NEW_IDNO = " ";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("1")) {
            CICTRID.DATA.NEW_IDTYP = "11201";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("2")) {
            CICTRID.DATA.NEW_IDTYP = "25000";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("3")) {
            CICTRID.DATA.NEW_IDTYP = "20100";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("4")) {
            CICTRID.DATA.NEW_IDTYP = "29200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("5")) {
            CICTRID.DATA.NEW_IDTYP = "24000";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("6")) {
            CICTRID.DATA.NEW_IDTYP = "10100";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("7")) {
            CICTRID.DATA.NEW_IDTYP = "20001";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("95")) {
            CICTRID.DATA.NEW_IDTYP = "10400";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("92")) {
            CICTRID.DATA.NEW_IDTYP = "11201";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("9")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0C")) {
            CICTRID.DATA.NEW_IDTYP = "10401";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0E")) {
            CICTRID.DATA.NEW_IDTYP = "11700";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0R")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0F")) {
            CICTRID.DATA.NEW_IDTYP = "25002";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0G")) {
            CICTRID.DATA.NEW_IDTYP = "25003";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0H")) {
            CICTRID.DATA.NEW_IDTYP = "25004";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0I")) {
            CICTRID.DATA.NEW_IDTYP = "25005";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0J")) {
            CICTRID.DATA.NEW_IDTYP = "25009";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0K")) {
            CICTRID.DATA.NEW_IDTYP = "10500";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0L")) {
            CICTRID.DATA.NEW_IDTYP = "25006";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0M")) {
            CICTRID.DATA.NEW_IDTYP = "11202";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0P")) {
            CICTRID.DATA.NEW_IDTYP = "10400";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0S")) {
            CICTRID.DATA.NEW_IDTYP = "25007";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0U")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0W")) {
            CICTRID.DATA.NEW_IDTYP = "20506";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 2).equalsIgnoreCase("0X")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("P")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("Y")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("Z")) {
            CICTRID.DATA.NEW_IDTYP = "19200";
        } else {
            CICTRID.DATA.NEW_IDTYP = "19200";
        }
        CEP.TRC(SCCGWA, CICTRID.DATA.NEW_IDTYP);
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        if (CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("7") 
                && CICTRID.DATA.OLD_IDNO.substring(11 - 1, 11 + 1 - 1).trim().length() == 0) {
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
            if (CICTRID.DATA.NEW_IDNO == null) CICTRID.DATA.NEW_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.NEW_IDNO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICTRID.DATA.NEW_IDNO += " ";
            CICTRID.DATA.NEW_IDNO = CICTRID.DATA.OLD_IDNO.substring(2 - 1, 2 + 8 - 1) + CICTRID.DATA.NEW_IDNO.substring(8);
            if (CICTRID.DATA.NEW_IDNO == null) CICTRID.DATA.NEW_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.NEW_IDNO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICTRID.DATA.NEW_IDNO += " ";
            CICTRID.DATA.NEW_IDNO = CICTRID.DATA.NEW_IDNO.substring(0, 9 - 1) + "-" + CICTRID.DATA.NEW_IDNO.substring(9 + 1 - 1);
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
            if (CICTRID.DATA.NEW_IDNO == null) CICTRID.DATA.NEW_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.NEW_IDNO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICTRID.DATA.NEW_IDNO += " ";
            CICTRID.DATA.NEW_IDNO = CICTRID.DATA.NEW_IDNO.substring(0, 10 - 1) + CICTRID.DATA.OLD_IDNO.substring(10 - 1, 10 + 1 - 1) + CICTRID.DATA.NEW_IDNO.substring(10 + 1 - 1);
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
        } else if ((CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("0") 
                || CICTRID.DATA.OLD_IDNO.substring(0, 1).equalsIgnoreCase("9"))) {
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
            CICTRID.DATA.NEW_IDNO = CICTRID.DATA.OLD_IDNO.substring(3 - 1, 3 + 17 - 1);
        } else {
            if (CICTRID.DATA.OLD_IDNO == null) CICTRID.DATA.OLD_IDNO = "";
            JIBS_tmp_int = CICTRID.DATA.OLD_IDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) CICTRID.DATA.OLD_IDNO += " ";
            CICTRID.DATA.NEW_IDNO = CICTRID.DATA.OLD_IDNO.substring(2 - 1, 2 + 18 - 1);
        }
        CEP.TRC(SCCGWA, CICTRID.DATA.NEW_IDNO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
