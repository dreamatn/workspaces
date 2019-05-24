package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTMAP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "AUTH DATA ITEM  MAINTAIN";
    String K_CPY_BPRTMAP = "BPRTMAP ";
    String K_OUTPUT_FMT = "BP552";
    String CPN_R_TMAP_MAINTANCE = "BP-R-TMAP-MAINTAIN  ";
    String CPN_P_BRW_PC = "BP-R-STARTBR-TMAP   ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    char WS_TBL_TMAP_FLAG = ' ';
    int WS_CNT = 0;
    short WS_SEQ = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCRAND SCCRAND = new SCCRAND();
    BPRTMAP BPRTMAP = new BPRTMAP();
    BPRTMAP BPROMAP = new BPRTMAP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUTCVC BPCUTCVC = new BPCUTCVC();
    BPCRTMAP BPCRTMAP = new BPCRTMAP();
    BPCRTMAB BPCRTMAB = new BPCRTMAB();
    BPCOTMAB BPCOTMAB = new BPCOTMAB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSTMAP BPCSTMAP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTMAP BPCSTMAP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTMAP = BPCSTMAP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTMAP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTMAP);
        IBS.init(SCCGWA, BPCRTMAP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        if (BPCSTMAP.FUNC == 'A') {
            B010_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTMAP.FUNC == 'U') {
            B020_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTMAP.FUNC == 'D') {
            B030_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTMAP.FUNC == 'Q') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            R100_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
        } else if (BPCSTMAP.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMAP);
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRTMAP.INFO.FUNC = 'C';
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        S000_CALL_BPZRTMAP();
        if (pgmRtn) return;
        B010_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTMAP;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + BPRTMAP.KEY.FROM_AREA;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPRTMAP.KEY.TX_CODE == null) BPRTMAP.KEY.TX_CODE = "";
        JIBS_tmp_int = BPRTMAP.KEY.TX_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPRTMAP.KEY.TX_CODE += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 2 - 1) + BPRTMAP.KEY.TX_CODE + BPCPNHIS.INFO.REF_NO.substring(2 + 7 - 1);
        WS_SEQ = BPRTMAP.KEY.DATA_SEQ;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + WS_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 9 - 1) + JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(9 + 3 - 1);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMAP);
        BPCRTMAP.INFO.FUNC = 'R';
        BPRTMAP.KEY.FROM_AREA = BPCSTMAP.FROM_AREA;
        BPRTMAP.KEY.TX_CODE = BPCSTMAP.TX_CODE;
        BPRTMAP.KEY.DATA_NM = BPCSTMAP.DATA_NM;
        BPRTMAP.KEY.DATA_SEQ = BPCSTMAP.DATA_SEQ;
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        S000_CALL_BPZRTMAP();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRTMAP, BPROMAP);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        BPCRTMAP.INFO.FUNC = 'M';
        S000_CALL_BPZRTMAP();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTMAP;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROMAP;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRTMAP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMAP);
        BPCRTMAP.INFO.FUNC = 'R';
        BPRTMAP.KEY.FROM_AREA = BPCSTMAP.FROM_AREA;
        BPRTMAP.KEY.TX_CODE = BPCSTMAP.TX_CODE;
        BPRTMAP.KEY.DATA_NM = BPCSTMAP.DATA_NM;
        BPRTMAP.KEY.DATA_SEQ = BPCSTMAP.DATA_SEQ;
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        S000_CALL_BPZRTMAP();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
        BPCRTMAP.INFO.FUNC = 'D';
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        S000_CALL_BPZRTMAP();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTMAP;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + BPRTMAP.KEY.FROM_AREA;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(1);
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        if (BPRTMAP.KEY.TX_CODE == null) BPRTMAP.KEY.TX_CODE = "";
        JIBS_tmp_int = BPRTMAP.KEY.TX_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPRTMAP.KEY.TX_CODE += " ";
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 2 - 1) + BPRTMAP.KEY.TX_CODE + BPCPNHIS.INFO.REF_NO.substring(2 + 7 - 1);
        WS_SEQ = BPRTMAP.KEY.DATA_SEQ;
        if (BPCPNHIS.INFO.REF_NO == null) BPCPNHIS.INFO.REF_NO = "";
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO += " ";
        JIBS_tmp_str[0] = "" + WS_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCPNHIS.INFO.REF_NO = BPCPNHIS.INFO.REF_NO.substring(0, 9 - 1) + JIBS_tmp_str[0] + BPCPNHIS.INFO.REF_NO.substring(9 + 3 - 1);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMAP);
        BPCRTMAP.INFO.FUNC = 'Q';
        BPRTMAP.KEY.FROM_AREA = BPCSTMAP.FROM_AREA;
        BPRTMAP.KEY.TX_CODE = BPCSTMAP.TX_CODE;
        BPRTMAP.KEY.DATA_NM = BPCSTMAP.DATA_NM;
        BPRTMAP.KEY.DATA_SEQ = BPCSTMAP.DATA_SEQ;
        BPCRTMAP.INFO.POINTER = BPRTMAP;
        BPCRTMAP.INFO.LEN = 197;
        S000_CALL_BPZRTMAP();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        WS_TBL_TMAP_FLAG = 'Y';
        B050_01_STARTBR_PROC();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_TMAP_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_02_READNEXT_PROC();
            if (pgmRtn) return;
            if (WS_TBL_TMAP_FLAG == 'Y') {
                WS_CNT = WS_CNT + 1;
                if (WS_CNT == 1) {
                    B050_04_OUT_TITLE();
                    if (pgmRtn) return;
                }
                B050_05_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
        }
        B050_03_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B050_01_STARTBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTMAP);
        if (BPCSTMAP.FROM_AREA == ' ') {
            BPRTMAP.KEY.FROM_AREA = ALL.charAt(0);
        } else {
            BPRTMAP.KEY.FROM_AREA = BPCSTMAP.FROM_AREA;
        }
        if (BPCSTMAP.TX_CODE.trim().length() == 0) {
            BPRTMAP.KEY.TX_CODE = "%%%%%%%";
        } else {
            BPRTMAP.KEY.TX_CODE = BPCSTMAP.TX_CODE;
        }
        if (BPCSTMAP.DATA_NM.trim().length() == 0) {
            BPRTMAP.KEY.DATA_NM = "%%%%%%%%%%%%%%%%%%%%";
        } else {
            BPRTMAP.KEY.DATA_NM = BPCSTMAP.DATA_NM;
        }
        if (BPCSTMAP.DATA_SEQ <= 0) {
            BPRTMAP.KEY.DATA_SEQ = 0;
        } else {
            BPRTMAP.KEY.DATA_SEQ = BPCSTMAP.DATA_SEQ;
        }
        BPCRTMAB.INFO.FUNC = 'S';
        S000_CALL_BPZRTMAB();
        if (pgmRtn) return;
    }
    public void B050_02_READNEXT_PROC() throws IOException,SQLException,Exception {
        BPCRTMAB.INFO.FUNC = 'N';
        S000_CALL_BPZRTMAB();
        if (pgmRtn) return;
    }
    public void B050_03_ENDBR_PROC() throws IOException,SQLException,Exception {
        BPCRTMAB.INFO.FUNC = 'E';
        S000_CALL_BPZRTMAB();
        if (pgmRtn) return;
    }
    public void B050_04_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 154;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_05_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTMAB);
        BPCOTMAB.FROM_AREA = BPRTMAP.KEY.FROM_AREA;
        BPCOTMAB.TX_CODE = BPRTMAP.KEY.TX_CODE;
        BPCOTMAB.DATA_NM = BPRTMAP.KEY.DATA_NM;
        BPCOTMAB.DATA_SEQ = BPRTMAP.KEY.DATA_SEQ;
        BPCOTMAB.DATA_DESC = BPRTMAP.DATA_DESC;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTMAB);
        SCCMPAG.DATA_LEN = 154;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTMAP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TMAP_MAINTANCE, BPCRTMAP);
        if (BPCRTMAP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTMAP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTMAB() throws IOException,SQLException,Exception {
        BPCRTMAB.INFO.LEN = 197;
        BPCRTMAB.INFO.POINTER = BPRTMAP;
        IBS.CALLCPN(SCCGWA, CPN_P_BRW_PC, BPCRTMAB);
        if (BPCRTMAB.RETURN_INFO == 'F') {
            WS_TBL_TMAP_FLAG = 'Y';
        } else if (BPCRTMAB.RETURN_INFO == 'N') {
            WS_TBL_TMAP_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRTMAP.KEY.FROM_AREA = BPCSTMAP.FROM_AREA;
        BPRTMAP.KEY.TX_CODE = BPCSTMAP.TX_CODE;
        BPRTMAP.KEY.DATA_NM = BPCSTMAP.DATA_NM;
        BPRTMAP.KEY.DATA_SEQ = BPCSTMAP.DATA_SEQ;
        BPRTMAP.PB_TYPE = BPCSTMAP.PB_TYPE;
        BPRTMAP.DATA_SDESC = BPCSTMAP.DATA_SDESC;
        BPRTMAP.DATA_DESC = BPCSTMAP.DATA_DESC;
    }
    public void R100_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTMAP.FROM_AREA = BPRTMAP.KEY.FROM_AREA;
        BPCSTMAP.TX_CODE = BPRTMAP.KEY.TX_CODE;
        BPCSTMAP.DATA_NM = BPRTMAP.KEY.DATA_NM;
        BPCSTMAP.DATA_SEQ = BPRTMAP.KEY.DATA_SEQ;
        BPCSTMAP.PB_TYPE = BPRTMAP.PB_TYPE;
        BPCSTMAP.DATA_SDESC = BPRTMAP.DATA_SDESC;
        BPCSTMAP.DATA_DESC = BPRTMAP.DATA_DESC;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
