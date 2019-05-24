package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPDM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PDME_BROWER = "BP-T-MAINT-PRDT-MENU";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "PDM  INFOMATION MAINTAIN";
    String K_CPY_BPRPPDMM = "BPRPPDMM";
    String K_PARM_TYPE_PRDM = "PRDM";
    String K_CODE_TYPE_CP = "CONTG";
    String WS_ERR_MSG = " ";
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPPDMM BPRPPDMM = new BPRPPDMM();
    BPRPPDMM BPROPRDM = new BPRPPDMM();
    BPRPDME BPRPDME = new BPRPDME();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTPDME BPCTPDME = new BPCTPDME();
    BPCO700 BPCO700 = new BPCO700();
    BPCPRDMO BPCPRDMO = new BPCPRDMO();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCSMPDM BPCSMPDM;
    public void MP(SCCGWA SCCGWA, BPCSMPDM BPCSMPDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPDM = BPCSMPDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMPDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMPDM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMPDM.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPDM.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPDM.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPDM.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPDM.I_FUNC == 'B') {
            B070_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPDM.I_FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "END OUTPUT");
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMPDM.PRDT_MODEL.trim().length() == 0 
            && BPCSMPDM.I_FUNC != 'B') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_MODEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPDMM);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPPDMM.KEY.TYP = "PRDM ";
        BPCPRMM.FUNC = '3';
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        if (BPCSMPDM.PRDT_MODEL == null) BPCSMPDM.PRDT_MODEL = "";
        JIBS_tmp_int = BPCSMPDM.PRDT_MODEL.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMPDM.PRDT_MODEL += " ";
        BPRPPDMM.KEY.CD = BPCSMPDM.PRDT_MODEL + BPRPPDMM.KEY.CD.substring(4);
        BPCPRMM.EFF_DT = BPCSMPDM.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPPDMM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_MODEL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPPDMM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPRPPDMM.KEY.TYP = "PRDM ";
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        if (BPCSMPDM.PRDT_MODEL == null) BPCSMPDM.PRDT_MODEL = "";
        JIBS_tmp_int = BPCSMPDM.PRDT_MODEL.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMPDM.PRDT_MODEL += " ";
        BPRPPDMM.KEY.CD = BPCSMPDM.PRDT_MODEL + BPRPPDMM.KEY.CD.substring(4);
        BPCPRMM.EFF_DT = BPCSMPDM.EFF_DT;
        CEP.TRC(SCCGWA, BPCSMPDM.EFF_DT);
        BPCPRMM.EXP_DT = BPCSMPDM.EXP_DT;
        CEP.TRC(SCCGWA, BPCSMPDM.EXP_DT);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPPDMM.DATA_TXT.OPEN_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPRPPDMM.DATA_TXT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPPDMM.DATA_TXT.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRPPDMM.DATA_LEN = 174;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPPDMM);
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMPDM.PRDT_MODEL;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPPDMM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B031_CHECK_INPUT_AGAIN() throws IOException,SQLException,Exception {
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPPDMM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRPPDMM.KEY.TYP = "PRDM ";
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        if (BPCSMPDM.PRDT_MODEL == null) BPCSMPDM.PRDT_MODEL = "";
        JIBS_tmp_int = BPCSMPDM.PRDT_MODEL.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMPDM.PRDT_MODEL += " ";
        BPRPPDMM.KEY.CD = BPCSMPDM.PRDT_MODEL + BPRPPDMM.KEY.CD.substring(4);
        CEP.TRC(SCCGWA, BPCSMPDM.EFF_DT);
        BPCPRMM.EFF_DT = BPCSMPDM.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPPDMM);
        IBS.CLONE(SCCGWA, BPRPPDMM, BPROPRDM);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMM);
        BPRPPDMM.DATA_TXT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPPDMM.DATA_TXT.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPPDMM.DATA_TXT.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPRMM.FUNC = '2';
        BPRPPDMM.KEY.TYP = "PRDM ";
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        if (BPCSMPDM.PRDT_MODEL == null) BPCSMPDM.PRDT_MODEL = "";
        JIBS_tmp_int = BPCSMPDM.PRDT_MODEL.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMPDM.PRDT_MODEL += " ";
        BPRPPDMM.KEY.CD = BPCSMPDM.PRDT_MODEL + BPRPPDMM.KEY.CD.substring(4);
        BPCPRMM.EFF_DT = BPCSMPDM.EFF_DT;
        BPCPRMM.EXP_DT = BPCSMPDM.EXP_DT;
        BPRPPDMM.DATA_LEN = 174;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPPDMM);
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMPDM.PRDT_MODEL;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPPDMM;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROPRDM;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPPDMM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPDMM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRPPDMM.KEY.TYP = "PRDM ";
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        if (BPCSMPDM.PRDT_MODEL == null) BPCSMPDM.PRDT_MODEL = "";
        JIBS_tmp_int = BPCSMPDM.PRDT_MODEL.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMPDM.PRDT_MODEL += " ";
        BPRPPDMM.KEY.CD = BPCSMPDM.PRDT_MODEL + BPRPPDMM.KEY.CD.substring(4);
        BPCPRMM.EFF_DT = BPCSMPDM.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPPDMM);
        B051_CHECK_PRDM_STUS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '1';
        BPRPPDMM.KEY.TYP = "PRDM ";
        BPCPRMM.EFF_DT = BPCSMPDM.EFF_DT;
        BPRPPDMM.DATA_LEN = 174;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B050_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMPDM.PRDT_MODEL;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPPDMM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMPDM.I_FUNC == 'I') {
            SCCFMT.FMTID = "BPX01";
        } else {
            SCCFMT.FMTID = "BP700";
        }
        SCCFMT.DATA_PTR = BPCO700;
        SCCFMT.DATA_LEN = 282;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B051_CHECK_PRDM_STUS() throws IOException,SQLException,Exception {
        B051_01_START_BROWSE();
        if (pgmRtn) return;
        while (BPCTPDME.RETURN_INFO != 'N') {
            B051_02_READ_NEXT();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDM_HAVE_PRODUCT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B051_03_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B051_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDME);
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCTPDME.INFO.INDEX_FLG = '3';
        BPRPDME.PRDT_MODEL = BPCSMPDM.PRDT_MODEL;
        BPCTPDME.INFO.POINTER = BPRPDME;
        BPCTPDME.LEN = 516;
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B051_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'N';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B051_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B070_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PARM_TYPE_PRDM;
        BPRPARM.KEY.CODE = BPCSMPDM.PRDT_MODEL;
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        WS_OUTPUT_FLG = 'T';
        B221_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRPARM);
            IBS.init(SCCGWA, BPCRMBPM);
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            if (!BPRPARM.KEY.TYPE.equalsIgnoreCase(K_PARM_TYPE_PRDM)) {
                WS_STOP_FLG = 'Y';
            }
            if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
            JIBS_tmp_int = BPRPARM.KEY.CODE.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
            if (WS_STOP_FLG != 'Y' 
                && BPRPARM.KEY.TYPE.equalsIgnoreCase(K_PARM_TYPE_PRDM) 
                && (BPRPARM.KEY.CODE.substring(0, 4).equalsIgnoreCase(BPCSMPDM.PRDT_MODEL) 
                || BPCSMPDM.PRDT_MODEL.trim().length() == 0)) {
                WS_OUTPUT_FLG = 'D';
                B221_FMT_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'N' 
            || BPCRMBPM.RETURN_INFO == 'L') {
            WS_STOP_FLG = 'Y';
        } else {
            if (BPCRMBPM.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        if (BPCSMPDM.PRDT_MODEL == null) BPCSMPDM.PRDT_MODEL = "";
        JIBS_tmp_int = BPCSMPDM.PRDT_MODEL.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMPDM.PRDT_MODEL += " ";
        BPRPPDMM.KEY.CD = BPCSMPDM.PRDT_MODEL + BPRPPDMM.KEY.CD.substring(4);
        BPRPPDMM.DESC = BPCSMPDM.ENG_DESC;
        BPRPPDMM.CDESC = BPCSMPDM.CHN_DESC;
        BPRPPDMM.DATA_TXT.DESC_MODEL = BPCSMPDM.DESC;
        BPRPPDMM.DATA_TXT.PARM_TX = BPCSMPDM.PARM_TX;
        BPRPPDMM.DATA_TXT.CREATE_TX = BPCSMPDM.CREATE_TX;
        BPRPPDMM.DATA_TXT.INNER_PRDT_IND = BPCSMPDM.INNER_PRDT_IND;
        BPRPPDMM.DATA_TXT.DEFT_PRDT = BPCSMPDM.DEFT_PRDT;
        CEP.TRC(SCCGWA, BPCSMPDM.CONTRACT_GROUP);
        BPRPPDMM.DATA_TXT.CONT_GROUP = BPCSMPDM.CONTRACT_GROUP;
        BPRPPDMM.DATA_TXT.MODEL_TYP = BPCSMPDM.MODEL_TYP;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO700);
        CEP.TRC(SCCGWA, BPRPPDMM);
        BPCO700.FUNC = BPCSMPDM.I_FUNC;
        BPCO700.PARM_TYPE = BPRPPDMM.KEY.TYP;
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        BPCO700.PRDT_MODEL = BPRPPDMM.KEY.CD.substring(0, 4);
        BPCO700.CONT_GROUP = BPRPPDMM.DATA_TXT.CONT_GROUP;
        BPCO700.ENG_DESC = BPRPPDMM.DESC;
        BPCO700.CHN_DESC = BPRPPDMM.CDESC;
        CEP.TRC(SCCGWA, BPRPPDMM.DESC);
        CEP.TRC(SCCGWA, BPRPPDMM.CDESC);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.INNER_PRDT_IND);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.PARM_TX);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.DESC_MODEL);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.DEFT_PRDT);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.LAST_DATE);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.LAST_TLR);
        CEP.TRC(SCCGWA, BPCO700.ENG_DESC);
        CEP.TRC(SCCGWA, BPCO700.CHN_DESC);
        BPCO700.FLAG_01 = 0X02;
        BPCO700.EFF_DT = BPCSMPDM.EFF_DT;
        BPCO700.EXP_DT = BPCPRMM.EXP_DT;
        BPCO700.DESC = BPRPPDMM.DATA_TXT.DESC_MODEL;
        BPCO700.FLAG_02 = 0X02;
        BPCO700.PARM_TX = BPRPPDMM.DATA_TXT.PARM_TX;
        BPCO700.CREATE_TX = BPRPPDMM.DATA_TXT.CREATE_TX;
        BPCO700.INNER_PRDT_IND = BPRPPDMM.DATA_TXT.INNER_PRDT_IND;
        BPCO700.DEFT_PRDT = BPRPPDMM.DATA_TXT.DEFT_PRDT;
        BPCO700.OPEN_DATE = BPRPPDMM.DATA_TXT.OPEN_DATE;
        BPCO700.LAST_DATE = BPRPPDMM.DATA_TXT.LAST_DATE;
        BPCO700.LAST_TLR = BPRPPDMM.DATA_TXT.LAST_TLR;
        CEP.TRC(SCCGWA, BPCO700.DESC);
        CEP.TRC(SCCGWA, BPCO700.LAST_DATE);
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.LAST_TLR);
        CEP.TRC(SCCGWA, BPCO700.LAST_TLR);
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "FMT OUTPUT XXXXXXXXXX");
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 118;
            SCCMPAG.SCR_ROW_CNT = 0;
            SCCMPAG.SCR_COL_CNT = 0;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, BPCPRDMO);
            IBS.init(SCCGWA, BPRPPDMM);
            BPCPRDMO.PRDT_MODEL = BPRPARM.KEY.CODE;
            BPCPRDMO.ENG_DESC = BPRPARM.DESC;
            BPCPRDMO.CHN_DESC = BPRPARM.CDESC;
            BPCPRDMO.EFF_DT = BPRPARM.EFF_DATE;
            BPCPRDMO.EXP_DT = BPRPARM.EXP_DATE;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRPPDMM.DATA_TXT);
            BPCPRDMO.CONT_GROUP = BPRPPDMM.DATA_TXT.CONT_GROUP;
            CEP.TRC(SCCGWA, BPCPRDMO);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCPRDMO);
            SCCMPAG.DATA_LEN = 118;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTPDME() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDME_BROWER, BPCTPDME);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
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
