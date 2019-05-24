package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSTAMT {
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "AMT AUTH INFO MAINTAIN  ";
    String K_CPY_BPRPTAMT = "BPRPTAMT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_TAMT = "BP-R-MGM-TAMT    ";
    String CPN_R_TAMB = "BP-R-MGM-TAMB    ";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String K_OUTPUT_FMT = "BP536";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 10;
    int K_COL_CNT = 7;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    char WS_BROWSE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPTAMT BPRPTAMT = new BPRPTAMT();
    BPRPTAMT BPRPOAMT = new BPRPTAMT();
    BPCRTAMT BPCRTAMT = new BPCRTAMT();
    BPCRTAMB BPCRTAMB = new BPCRTAMB();
    BPCOTAMT BPCOTAMT = new BPCOTAMT();
    BPCOTAM1 BPCOTAM1 = new BPCOTAM1();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSTAMT BPCSTAMT;
    public void MP(SCCGWA SCCGWA, BPCSTAMT BPCSTAMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTAMT = BPCSTAMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTAMT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRTAMT);
        IBS.init(SCCGWA, BPCRTAMB);
        BPCSTAMT.RC.RC_CODE = 0;
        if (BPCSTAMT.BR == 0 
            || BPCSTAMT.BR == 0X00) {
            BPCSTAMT.BR = SCCGWA.COMM_AREA.HQT_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSTAMT.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTAMT.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTAMT.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTAMT.FUNC == 'D') {
            B035_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTAMT.FUNC == '1'
            || BPCSTAMT.FUNC == '2') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPTAMT);
        IBS.init(SCCGWA, BPCPRMR);
        BPRPTAMT.KEY.TYP = "AMTO";
        BPRPTAMT.KEY.REDEFINES6.APP = BPCSTAMT.TAMT_APP;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.NO = BPCSTAMT.TBL_NO;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.CHNL = BPCSTAMT.CHNL;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.BR = BPCSTAMT.BR;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPCPRMR.DAT_PTR = BPRPTAMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPTAMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPCPRMM.EXP_DT = BPCSTAMT.EXP_DATE;
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPRPTAMT.DATA_LEN = 342;
        BPCPRMM.DAT_PTR = BPRPTAMT;
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXX:BPRPTAMT:");
        CEP.TRC(SCCGWA, BPRPTAMT);
        CEP.TRC(SCCGWA, "YYYYYYYYYYYYYY:CREATE:");
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPTAMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRPTAMT.KEY.TYP = "AMTO";
        BPRPTAMT.KEY.REDEFINES6.APP = BPCSTAMT.TAMT_APP;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.NO = BPCSTAMT.TBL_NO;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.CHNL = BPCSTAMT.CHNL;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.BR = BPCSTAMT.BR;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPCPRMM.DAT_PTR = BPRPTAMT;
        CEP.TRC(SCCGWA, "MMMMMMMMMMMMMMMMMMMMMM:");
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "MMMMMMMMMMMMDDDDDDDDDD");
        IBS.CLONE(SCCGWA, BPRPTAMT, BPRPOAMT);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '2';
        BPCPRMM.EXP_DT = BPCSTAMT.EXP_DATE;
        BPRPTAMT.DATA_LEN = 342;
        BPCPRMM.DAT_PTR = BPRPTAMT;
        CEP.TRC(SCCGWA, "WWWWWWWWWWWWWWWWWWWWWW:");
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "WWWWWWWWWWWDDDDDDDDDDD:");
    }
    public void B035_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPTAMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRPTAMT.KEY.TYP = "AMTO";
        BPRPTAMT.KEY.REDEFINES6.APP = BPCSTAMT.TAMT_APP;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.NO = BPCSTAMT.TBL_NO;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.CHNL = BPCSTAMT.CHNL;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.BR = BPCSTAMT.BR;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPCPRMM.DAT_PTR = BPRPTAMT;
        CEP.TRC(SCCGWA, "DELLELELELELELELELELELE:");
        CEP.TRC(SCCGWA, BPRPTAMT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDDDDDDDDDDENDENDNENDDNENEENENENE");
        BPRPTAMT.KEY.TYP = "AMTO";
        BPCPRMM.FUNC = '1';
        BPRPTAMT.DATA_LEN = 342;
        BPCPRMM.DAT_PTR = BPRPTAMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSTAMT.FUNC == '1') {
            B040_01_BRW_VIEW();
            if (pgmRtn) return;
        } else if (BPCSTAMT.FUNC == '2') {
            B040_02_BRW_OTHER();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPTAMT);
        IBS.init(SCCGWA, BPCPRMB);
        BPRPTAMT.KEY.TYP = "AMTO";
        BPRPTAMT.KEY.REDEFINES6.APP = BPCSTAMT.TAMT_APP;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPCPRMB.FUNC = '0';
        CEP.TRC(SCCGWA, "BBBBBBBBBBBBBBB:");
        CEP.TRC(SCCGWA, BPRPTAMT.KEY);
        BPCPRMB.DAT_PTR = BPRPTAMT;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        while (BPCPRMB.RC.RC_RTNCODE == 0 
            && WS_BROWSE_FLAG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFFLGLLLLLLL:");
            CEP.TRC(SCCGWA, BPRPTAMT);
            CEP.TRC(SCCGWA, WS_BROWSE_FLAG);
            IBS.init(SCCGWA, BPCPRMB.RC);
            BPCPRMB.FUNC = '1';
            BPCPRMB.DAT_PTR = BPRPTAMT;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            if ((BPCSTAMT.TAMT_APP.trim().length() > 0 
                && BPCSTAMT.TAMT_APP.charAt(0) != 0X00) 
                && (!BPCSTAMT.TAMT_APP.equalsIgnoreCase(BPRPTAMT.KEY.REDEFINES6.APP))) {
                WS_BROWSE_FLAG = 'Y';
            }
            if (BPCPRMB.RC.RC_RTNCODE == 0 
                && (WS_BROWSE_FLAG != 'Y')) {
                B040_01_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '2';
        BPCPRMB.DAT_PTR = BPRPTAMT;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 140;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTAM1);
        BPCOTAM1.TAMT_APP = BPRPTAMT.KEY.REDEFINES6.APP;
        BPCOTAM1.CHNL = BPRPTAMT.KEY.REDEFINES6.CHNL;
        BPCOTAM1.TBL_NO = BPRPTAMT.KEY.REDEFINES6.NO;
        BPCOTAM1.BR = BPRPTAMT.KEY.REDEFINES6.BR;
        BPCOTAM1.DESC = BPRPTAMT.DATA_TXT.AMT_DESC;
        BPCOTAM1.FLAG = BPRPTAMT.DATA_TXT.FLAG;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTAM1);
        SCCMPAG.DATA_LEN = 140;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_BRW_OTHER() throws IOException,SQLException,Exception {
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSTAMT.FUNC == 'A') {
            B080_01_HISTORY_RECORD();
            if (pgmRtn) return;
        }
        if (BPCSTAMT.FUNC == 'U') {
            B080_02_HISTORY_RECORD();
            if (pgmRtn) return;
        }
        if (BPCSTAMT.FUNC == 'D') {
            B080_03_HISTORY_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B080_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPTAMT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.TAMT_APP == null) BPCSTAMT.TAMT_APP = "";
        JIBS_tmp_int = BPCSTAMT.TAMT_APP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSTAMT.TAMT_APP += " ";
        BPCPNHIS.INFO.REF_NO = BPCSTAMT.TAMT_APP + BPCPNHIS.INFO.REF_NO.substring(2);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.TBL_NO == null) BPCSTAMT.TBL_NO = "";
        JIBS_tmp_int = BPCSTAMT.TBL_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSTAMT.TBL_NO += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 3 - 1) + BPCSTAMT.TBL_NO + BPCPNHIS.INFO.REF_NO.substring(3 + 4 - 1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.CHNL == null) BPCSTAMT.CHNL = "";
        JIBS_tmp_int = BPCSTAMT.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPCSTAMT.CHNL += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 7 - 1) + BPCSTAMT.CHNL + BPCPNHIS.INFO.REF_NO.substring(7 + 5 - 1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + BPCSTAMT.BR;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(12 + JIBS_tmp_str[0].length() - 1);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B080_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPTAMT;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.TAMT_APP == null) BPCSTAMT.TAMT_APP = "";
        JIBS_tmp_int = BPCSTAMT.TAMT_APP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSTAMT.TAMT_APP += " ";
        BPCPNHIS.INFO.REF_NO = BPCSTAMT.TAMT_APP + BPCPNHIS.INFO.REF_NO.substring(2);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.TBL_NO == null) BPCSTAMT.TBL_NO = "";
        JIBS_tmp_int = BPCSTAMT.TBL_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSTAMT.TBL_NO += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 3 - 1) + BPCSTAMT.TBL_NO + BPCPNHIS.INFO.REF_NO.substring(3 + 4 - 1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.CHNL == null) BPCSTAMT.CHNL = "";
        JIBS_tmp_int = BPCSTAMT.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPCSTAMT.CHNL += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 7 - 1) + BPCSTAMT.CHNL + BPCPNHIS.INFO.REF_NO.substring(7 + 5 - 1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + BPCSTAMT.BR;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(12 + JIBS_tmp_str[0].length() - 1);
        BPCPNHIS.INFO.OLD_DAT_PT = BPRPOAMT;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPTAMT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B080_03_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPTAMT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.TAMT_APP == null) BPCSTAMT.TAMT_APP = "";
        JIBS_tmp_int = BPCSTAMT.TAMT_APP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSTAMT.TAMT_APP += " ";
        BPCPNHIS.INFO.REF_NO = BPCSTAMT.TAMT_APP + BPCPNHIS.INFO.REF_NO.substring(2);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.TBL_NO == null) BPCSTAMT.TBL_NO = "";
        JIBS_tmp_int = BPCSTAMT.TBL_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSTAMT.TBL_NO += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 3 - 1) + BPCSTAMT.TBL_NO + BPCPNHIS.INFO.REF_NO.substring(3 + 4 - 1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPCSTAMT.CHNL == null) BPCSTAMT.CHNL = "";
        JIBS_tmp_int = BPCSTAMT.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPCSTAMT.CHNL += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 7 - 1) + BPCSTAMT.CHNL + BPCPNHIS.INFO.REF_NO.substring(7 + 5 - 1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + BPCSTAMT.BR;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 12 - 1) + JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(12 + JIBS_tmp_str[0].length() - 1);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTAMT);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTAMT;
        SCCFMT.DATA_LEN = 459;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRPTAMT.KEY.TYP = "AMTO";
        BPRPTAMT.KEY.REDEFINES6.APP = BPCSTAMT.TAMT_APP;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.NO = BPCSTAMT.TBL_NO;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.CHNL = BPCSTAMT.CHNL;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.KEY.REDEFINES6.BR = BPCSTAMT.BR;
        BPRPTAMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRPTAMT.KEY.REDEFINES6);
        BPRPTAMT.DATA_TXT.STS = BPCSTAMT.TAMT_STS;
        BPRPTAMT.DATA_TXT.AMT_DESC = BPCSTAMT.DESC;
        BPRPTAMT.DATA_TXT.FLAG = BPCSTAMT.FLAG;
        BPRPTAMT.DATA_TXT.FLD_CNT = BPCSTAMT.FLD_CNT;
        BPRPTAMT.DATA_TXT.EX_TYP = BPCSTAMT.EX_TYPE;
        BPRPTAMT.DATA_TXT.U_FLD_CNT = BPCSTAMT.U_FLD_CNT;
        BPRPTAMT.DATA_TXT.REDEFINES26.ITEM_LST_DATA[1-1].LIMIT = 0;
        BPRPTAMT.DATA_TXT.ITEM_LST = IBS.CLS2CPY(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPTAMT.DATA_TXT.REDEFINES26);
