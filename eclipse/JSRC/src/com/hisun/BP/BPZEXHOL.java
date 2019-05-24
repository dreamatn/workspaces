package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZEXHOL {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY       ";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_CNTY_CD = " ";
    String WS_CITY_CD = " ";
    int WS_HOL_DATE = 0;
    char WS_DATE_HOLIDAY = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    SCCGWA SCCGWA;
    BPCEXCHK BPCEXCHK;
    public void MP(SCCGWA SCCGWA, BPCEXCHK BPCEXCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXCHK = BPCEXCHK;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZEXHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK.RC);
        BPCEXCHK.DATA_FLG = ' ';
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCEXCHK.DATA_FLG = '1';
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_MAP_INPUT_DATA();
        if (pgmRtn) return;
        B300_CHK_DATA_VALID();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN CHECK HOL DATA");
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_TYPE);
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA);
        if (!BPCEXCHK.EXCEL_TYPE.equalsIgnoreCase("02")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_NOT_CP_HOLIDAY, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXCHK.EXCEL_DATA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAP_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        WS_CNTY_CD = BPCEXCHK.EXCEL_DATA.substring(0, 4);
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        WS_CITY_CD = BPCEXCHK.EXCEL_DATA.substring(5 - 1, 5 + 4 - 1);
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        if (BPCEXCHK.EXCEL_DATA.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) WS_HOL_DATE = 0;
        else WS_HOL_DATE = Integer.parseInt(BPCEXCHK.EXCEL_DATA.substring(9 - 1, 9 + 8 - 1));
        if (BPCEXCHK.EXCEL_DATA == null) BPCEXCHK.EXCEL_DATA = "";
        JIBS_tmp_int = BPCEXCHK.EXCEL_DATA.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPCEXCHK.EXCEL_DATA += " ";
        WS_DATE_HOLIDAY = BPCEXCHK.EXCEL_DATA.substring(17 - 1, 17 + 1 - 1).charAt(0);
    }
    public void B300_CHK_DATA_VALID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNTY_CD);
        if (WS_CNTY_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIQCNT);
            BPCIQCNT.INPUT_DAT.CNTY_CD = WS_CNTY_CD;
            S000_CALL_BPZIQCNT();
            if (pgmRtn) return;
            if (BPCIQCNT.RC.RC_CODE > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_INVALID, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_CITY_CD);
        if (WS_CITY_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIQCIT);
            BPCIQCIT.INPUT_DAT.CNTY_CD = WS_CNTY_CD;
            BPCIQCIT.INPUT_DAT.CITY_CD = WS_CITY_CD;
            S000_CALL_BPZIQCIT();
            if (pgmRtn) return;
            if (BPCIQCIT.RC.RC_CODE > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CITY_INVALID, BPCEXCHK.RC);
                BPCEXCHK.DATA_FLG = '0';
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_HOL_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_HOL_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_DATE_HOLIDAY != 'W' 
            && WS_DATE_HOLIDAY != 'S' 
            && WS_DATE_HOLIDAY != 'H') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, BPCEXCHK.RC);
            BPCEXCHK.DATA_FLG = '0';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXCHK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCEXCHK = ");
            CEP.TRC(SCCGWA, BPCEXCHK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
