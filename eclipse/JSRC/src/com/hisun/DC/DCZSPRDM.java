package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPRDM {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC052";
    int K_MAX_COL = 20;
    int K_MAX_ROW = 20;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "THE CARD BIN PARAMETER MAINTAIN";
    String K_HIS_COPYBOOK = "DCRPRDPR";
    String K_PARM_PRDPR = "PRDPR";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSPRDM_WS_OUTPUT_VAL WS_OUTPUT_VAL = new DCZSPRDM_WS_OUTPUT_VAL();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCRPRDPR DCRPRDPO = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPRDM DCCSPRDM;
    public void MP(SCCGWA SCCGWA, DCCSPRDM DCCSPRDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPRDM = DCCSPRDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPRDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPRDM);
        CEP.TRC(SCCGWA, DCCSPRDM.FUNC);
        if (DCCSPRDM.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPRDM.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPRDM.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPRDM.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.FUNC_FALSE, 1);
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSPRDM.VAL.KEY.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_MUST_INPUT, 9);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCCSPRDM.VAL.DATA.MODEL;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSPRDM.VAL.KEY.PROD_CD;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND);
        }
        if (DCCUPRCD.RC.RC_CODE == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, DCCSPRDM.VAL.EFF_DATE);
        CEP.TRC(SCCGWA, DCCSPRDM.VAL.EXP_DATE);
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.DATE.EFF_DATE = DCCSPRDM.VAL.EFF_DATE;
        DCCUPRCD.DATE.EXP_DATE = DCCSPRDM.VAL.EXP_DATE;
        DCCUPRCD.TX_TYPE = 'A';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.KEY.PRDPR_TYP = "PRDPR";
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCCSPRDM.VAL.DATA.MODEL;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSPRDM.VAL.KEY.PROD_CD;
        DCCUPRCD.DATA.CDESC = DCCSPRDM.VAL.PRD_NM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSPRDM.VAL.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUPRCD.DATA.VAL);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.VAL.PRDMO_CD);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.KEY.CD.PARM_CODE);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA);
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPO);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCCSPRDM.VAL.DATA.MODEL;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSPRDM.VAL.KEY.PROD_CD;
        DCCUPRCD.DATE.EFF_DATE = DCCSPRDM.VAL.EFF_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND);
        }
        if (DCCUPRCD.RC.RC_CODE == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPO);
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'M';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCCSPRDM.VAL.DATA.MODEL;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSPRDM.VAL.KEY.PROD_CD;
        DCCUPRCD.DATE.EFF_DATE = DCCSPRDM.VAL.EFF_DATE;
        DCCUPRCD.DATE.EXP_DATE = DCCSPRDM.VAL.EXP_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSPRDM.VAL.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUPRCD.DATA.VAL);
        DCCUPRCD.DATA.CDESC = DCCSPRDM.VAL.PRD_NM;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPO);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCCSPRDM.VAL.DATA.MODEL;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSPRDM.VAL.KEY.PROD_CD;
        DCCUPRCD.DATE.EFF_DATE = DCCSPRDM.VAL.EFF_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND);
        }
        if (DCCUPRCD.RC.RC_CODE == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPO);
        }
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'D';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCCSPRDM.VAL.DATA.MODEL;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSPRDM.VAL.KEY.PROD_CD;
        DCCUPRCD.DATE.EFF_DATE = DCCSPRDM.VAL.EFF_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
    }
    public void B050_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = K_PARM_PRDPR;
        BPRPARM.KEY.CODE = "999999" + DCCSPRDM.VAL.KEY.PROD_CD;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        if (BPCRMBPM.RETURN_INFO == 'F') {
            B050_01_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        WS_CNT = 0;
        while (BPCRMBPM.RETURN_INFO != 'L' 
            && SCCMPAG.FUNC != 'E') {
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            if (BPRPARM.BLOB_VAL.substring(0, 4).equalsIgnoreCase("CARD")) {
                B050_01_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                WS_CNT = WS_CNT + 1;
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B050_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSPRDM.VAL.DATA);
        CEP.TRC(SCCGWA, "======TEST1=======");
        CEP.TRC(SCCGWA, BPRPARM.CDESC);
        CEP.TRC(SCCGWA, BPRPARM.CDESC);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        WS_OUTPUT_VAL.WS_PROD_CD = BPRPARM.KEY.CODE.substring(7 - 1, 7 + 10 - 1);
        WS_OUTPUT_VAL.WS_PROD_CD_NM = BPRPARM.CDESC;
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL.WS_PROD_CD_NM);
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL.WS_PROD_CD_NM);
        WS_OUTPUT_VAL.WS_EFF_DATE = BPRPARM.EFF_DATE;
        WS_OUTPUT_VAL.WS_EXP_DATE = BPRPARM.EXP_DATE;
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_OUTPUT_VAL.WS_DATA);
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_VAL);
        SCCMPAG.DATA_LEN = 133;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 183;
        if (DCCSPRDM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRPRDPR;
        }
        if (DCCSPRDM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRPRDPO;
        }
        if (DCCSPRDM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRPRDPO;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRPRDPR;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "=========TEST2===========");
        CEP.TRC(SCCGWA, DCRPRDPR.CDESC);
        CEP.TRC(SCCGWA, DCRPRDPR.CDESC);
        WS_OUTPUT_VAL.WS_PROD_CD = DCRPRDPR.KEY.CD.KEY1.PROD_CD;
        WS_OUTPUT_VAL.WS_PROD_CD_NM = DCRPRDPR.CDESC;
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL.WS_PROD_CD_NM);
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL.WS_PROD_CD_NM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRPRDPR.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_OUTPUT_VAL.WS_DATA);
        WS_OUTPUT_VAL.WS_EFF_DATE = BPCPRMM.EFF_DT;
        WS_OUTPUT_VAL.WS_EXP_DATE = BPCPRMM.EXP_DT;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_OUTPUT_VAL;
        SCCFMT.DATA_LEN = 133;
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        BPCPRMM.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN    ", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSMPRD);
        CEP.TRC(SCCGWA, BPCSMPRD.RC.RC_CODE);
        if (BPCSMPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMPRD.RC);
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
