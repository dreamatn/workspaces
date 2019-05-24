package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.IB.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT9040 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String TBL_IBTMST = "IBTMST  ";
    String TBL_IBTINSH = "IBTINSH ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WS_MTHS = 0;
    short WS_YEAR = 0;
    String WS_ERR_MSG = " ";
    int WS_WORK_DATE = 0;
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCQINF IBCQINF = new IBCQINF();
    IBCFIBL IBCFIBL = new IBCFIBL();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    IBCINFHI IBCINFHI = new IBCINFHI();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    TDB9040_AWA_9040 TDB9040_AWA_9040;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT9040 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB9040_AWA_9040>");
        TDB9040_AWA_9040 = (TDB9040_AWA_9040) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_COMPUTE_DATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB9040_AWA_9040.STR_DATE == 0 
            || TDB9040_AWA_9040.TERM.trim().length() == 0) {
            WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_COMPUTE_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDB9040_AWA_9040.STR_DATE;
            if (TDB9040_AWA_9040.TERM == null) TDB9040_AWA_9040.TERM = "";
            JIBS_tmp_int = TDB9040_AWA_9040.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDB9040_AWA_9040.TERM += " ";
        if (TDB9040_AWA_9040.TERM.substring(0, 1).equalsIgnoreCase("M")) {
            if (TDB9040_AWA_9040.TERM == null) TDB9040_AWA_9040.TERM = "";
            JIBS_tmp_int = TDB9040_AWA_9040.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDB9040_AWA_9040.TERM += " ";
            if (TDB9040_AWA_9040.TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) SCCCLDT.MTHS = 0;
            else SCCCLDT.MTHS = Short.parseShort(TDB9040_AWA_9040.TERM.substring(2 - 1, 2 + 3 - 1));
            if (TDB9040_AWA_9040.TERM == null) TDB9040_AWA_9040.TERM = "";
            JIBS_tmp_int = TDB9040_AWA_9040.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDB9040_AWA_9040.TERM += " ";
        } else if (TDB9040_AWA_9040.TERM.substring(0, 1).equalsIgnoreCase("D")) {
            if (TDB9040_AWA_9040.TERM == null) TDB9040_AWA_9040.TERM = "";
            JIBS_tmp_int = TDB9040_AWA_9040.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDB9040_AWA_9040.TERM += " ";
            if (TDB9040_AWA_9040.TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) SCCCLDT.DAYS = 0;
            else SCCCLDT.DAYS = Integer.parseInt(TDB9040_AWA_9040.TERM.substring(2 - 1, 2 + 3 - 1));
            if (TDB9040_AWA_9040.TERM == null) TDB9040_AWA_9040.TERM = "";
            JIBS_tmp_int = TDB9040_AWA_9040.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDB9040_AWA_9040.TERM += " ";
        } else if (TDB9040_AWA_9040.TERM.substring(0, 1).equalsIgnoreCase("Y")) {
            if (TDB9040_AWA_9040.TERM == null) TDB9040_AWA_9040.TERM = "";
            JIBS_tmp_int = TDB9040_AWA_9040.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDB9040_AWA_9040.TERM += " ";
            if (TDB9040_AWA_9040.TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_YEAR = 0;
            else WS_YEAR = Short.parseShort(TDB9040_AWA_9040.TERM.substring(2 - 1, 2 + 3 - 1));
            WS_MTHS = (short) (WS_YEAR * 12);
            SCCCLDT.MTHS = WS_MTHS;
        } else {
            WS_ERR_MSG = IBCMSG_ERROR_MSG.INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        S000_CALL_SCSSCLDT();
        TDB9040_AWA_9040.END_DT = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, TDB9040_AWA_9040.PC_FLG);
        if (TDB9040_AWA_9040.PC_FLG == 'C') {
            R00_CHECK_FROMDATE_HOLIDAY();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R00_CHECK_FROMDATE_HOLIDAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "CN";
        BPCOCLWD.DATE1 = TDB9040_AWA_9040.END_DT;
        BPCOCLWD.DAYS = 1;
        S00_LINK_BPZPCLWD();
        if (BPCOCLWD.DATE2_FLG == 'H') {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = "CN";
            BPCOCLWD.DATE1 = TDB9040_AWA_9040.END_DT;
            BPCOCLWD.WDAYS = 1;
            CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
            CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
            S00_LINK_BPZPCLWD();
            TDB9040_AWA_9040.END_DT = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, TDB9040_AWA_9040.END_DT);
        }
    }
    public void S00_LINK_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
