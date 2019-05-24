package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMAGT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITAGT_RD;
    DBParm CITJRL_RD;
    brParm CITAGT_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "CI AGT INFO        ";
    String K_HIS_CPY = "CIRAGT";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI502";
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String K_SEQ_TYPE = "CIAGT";
    int K_EXP_DATE = 20991231;
    int WS_I = 0;
    char WS_CHNL_WORD = ' ';
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    String WS_AGT_TYP = " ";
    String WS_CI_NO = " ";
    String WS_ENTY_NO = " ";
    CIZMAGT_WS_SIGN_CHNL_CTLW WS_SIGN_CHNL_CTLW = new CIZMAGT_WS_SIGN_CHNL_CTLW();
    String WS_SIGN_CHNL = " ";
    char WS_AGT_FLG = ' ';
    char WS_AGT_STS = ' ';
    char WS_ACR_STS = ' ';
    char WS_MX_FLG = ' ';
    char WS_SIGN_CHNL_FLG = ' ';
    char WS_JRL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CIRJRL CIRJRL = new CIRJRL();
    CIRAGT CIRAGT = new CIRAGT();
    CIRAGT CIRAGTO = new CIRAGT();
    CIRAGT CIRAGTN = new CIRAGT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICPAGT CICPAGT = new CICPAGT();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CICSSCH CICSSCH = new CICSSCH();
    CICOAGTL CICOAGTL = new CICOAGTL();
    CICOAGTR CICOAGTR = new CICOAGTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICUAGT CICUAGT = new CICUAGT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMAGT CICMAGT;
    public void MP(SCCGWA SCCGWA, CICMAGT CICMAGT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMAGT = CICMAGT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMAGT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRAGT);
        CICMAGT.RC.RC_MMO = "CI";
        CICMAGT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMAGT.FUNC);
        if (CICMAGT.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMAGT.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMAGT.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICMAGT.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_ENTY_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRACR);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
        if (CICMAGT.DATA.ENTY_TYP == '0') {
            CIRBAS.KEY.CI_NO = CICMAGT.DATA.ENTY_NO;
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && CICMAGT.DATA.AGT_STS == 'N' 
                && (CICMAGT.FUNC == 'A' 
                || CICMAGT.FUNC == 'M')) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_CLOSE_STS, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (CICMAGT.DATA.ENTY_TYP == '1'
            || CICMAGT.DATA.ENTY_TYP == '2'
            || CICMAGT.DATA.ENTY_TYP == '3'
            || CICMAGT.DATA.ENTY_TYP == ' ') {
            CIRACR.KEY.AGR_NO = CICMAGT.DATA.ENTY_NO;
            CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            T000_READ_CITACR();
            if (pgmRtn) return;
            WS_ACR_STS = CIRACR.STS;
            CICMAGT.DATA.CI_NO = CIRACR.CI_NO;
            CICMAGT.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
            if (CICMAGT.DATA.FRM_APP.trim().length() == 0) {
                CICMAGT.DATA.FRM_APP = CIRACR.FRM_APP;
            }
            CEP.TRC(SCCGWA, CIRACR.FRM_APP);
            CEP.TRC(SCCGWA, CIRACR.CI_NO);
            CEP.TRC(SCCGWA, CICMAGT.DATA.CI_NO);
            if (WS_ACR_STS == '1' 
                && CICMAGT.DATA.AGT_STS == 'N' 
                && (CICMAGT.FUNC == 'A' 
                || CICMAGT.FUNC == 'M')) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_IS_CLOSED, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CI_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRAGT.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && CICMAGT.DATA.AGT_STS == 'N' 
                && (CICMAGT.FUNC == 'M' 
                || CICMAGT.FUNC == 'A')) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_CLOSE_STS, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CICMAGT.DATA.CI_TYP = CIRBAS.CI_TYP;
    }
    public void R000_CHECK_AGT_PRMR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPAGT);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, K_SEQ_TYPE);
        BPRPRMT.KEY.TYP = K_SEQ_TYPE;
        BPRPRMT.KEY.CD = CICMAGT.DATA.AGT_TYP;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPAGT);
    }
    public void R000_GET_ORDNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICUAGT);
        CICUAGT.FUNC = 'N';
        CICUAGT.DATA.AGT_TYP = CICMAGT.DATA.AGT_TYP;
        S000_CALL_CIZUAGT();
        if (pgmRtn) return;
        CICMAGT.DATA.AGT_NO = CICUAGT.DATA.AGT_NO;
    }
    public void R000_CHECK_MX_AGT_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRAGT);
        if (CICMAGT.DATA.CI_NO.trim().length() > 0) {
            CIRAGT.CI_NO = CICMAGT.DATA.CI_NO;
        } else {
            CIRACR.KEY.AGR_NO = CICMAGT.DATA.ENTY_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CIRAGT.CI_NO = CIRACR.CI_NO;
        }
        CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
        CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
        CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_TYP);
        T000_STARTBR_CITAGT_ENTY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MX_FLG = 'N';
        }
        CEP.TRC(SCCGWA, CICPAGT.CLO_CTL);
        CEP.TRC(SCCGWA, CICPAGT.SIGN_CHNL);
        CEP.TRC(SCCGWA, CICPAGT.USE_CHNL);
        CEP.TRC(SCCGWA, CICPAGT.AGT_CLS);
        CEP.TRC(SCCGWA, CICPAGT.AGT_LVL);
        CEP.TRC(SCCGWA, CICPAGT.FRM_APP);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            for (WS_I = 1; WS_I <= 12 
                && CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP.trim().length() != 0; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP);
                if (CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP.trim().length() > 0) {
                    CEP.TRC(SCCGWA, CIRAGT.AGT_TYP);
                    CEP.TRC(SCCGWA, WS_AGT_TYP);
                    if (CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP.equalsIgnoreCase(CIRAGT.AGT_TYP)) {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_MUTEX, CICMAGT.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRAGTO);
        IBS.init(SCCGWA, CIRAGTN);
        R000_CHECK_ENTY_STS();
        if (pgmRtn) return;
        R000_CHECK_AGT_PRMR();
        if (pgmRtn) return;
        if (CICPAGT.MUTEX_DATA[1-1].MX_AGT_TYP.trim().length() > 0) {
            R000_CHECK_MX_AGT_TYP();
            if (pgmRtn) return;
        }
        if (CICMAGT.DATA.AGT_NO.trim().length() == 0) {
            R000_GET_ORDNO_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "AAAAA-ADD");
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        if (CICMAGT.DATA.AGT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_NO_NOT_GENERATE, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRAGT);
            CIRAGT.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
            T000_READ_CITAGT();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                CEP.TRC(SCCGWA, "AAAAA-ADD-NOTFND");
                R000_TRANS_DATA_TO_TBL();
                if (pgmRtn) return;
                T000_WRITE_CITAGT();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_EXIST, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_GET_CI_TYP();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_MAGT();
        if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.INP_AREA.BD_ECIF_SG0001.ECIF_OPTTYPE = '1';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        R000_RLTM_INFORM_ECIF();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B040_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRAGTO);
        IBS.init(SCCGWA, CIRAGTN);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
        if (CICMAGT.DATA.CI_NO.trim().length() > 0 
            && CICMAGT.DATA.AGT_NO.trim().length() > 0) {
            B040_10_MODIFY_BY_TRL();
            if (pgmRtn) return;
        } else {
            B040_20_MODIFY_BY_OTHER();
            if (pgmRtn) return;
        }
    }
    public void B040_10_MODIFY_BY_TRL() throws IOException,SQLException,Exception {
        R000_CHECK_ENTY_STS();
        if (pgmRtn) return;
        R000_CHECK_AGT_PRMR();
        if (pgmRtn) return;
        WS_AGT_TYP = CIRAGT.AGT_TYP;
        R000_CHECK_MX_AGT_TYP();
        if (pgmRtn) return;
        CIRAGT.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
        CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
        CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
        T000_READ_CITAGT_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_CITAGT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
        if (CICMAGT.DATA.AGT_STS == 'C') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_GET_CI_TYP();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_MAGT();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.INP_AREA.BD_ECIF_SG0001.ECIF_OPTTYPE = '3';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        R000_RLTM_INFORM_ECIF();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B040_20_MODIFY_BY_OTHER() throws IOException,SQLException,Exception {
        R000_CHECK_ENTY_STS();
        if (pgmRtn) return;
        CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
        CIRAGT.AGT_TYP = CICMAGT.DATA.AGT_TYP;
        CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
        WS_AGT_STS = 'N';
        CIRAGT.AGT_STS = WS_AGT_STS;
        CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
        CEP.TRC(SCCGWA, CIRAGT.AGT_TYP);
        CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRAGT.AGT_STS);
        T000_READ_CITAGT_UPD_INDX();
        if (pgmRtn) return;
        if (WS_AGT_FLG == 'Y') {
            CICMAGT.DATA.AGT_NO = CIRAGT.KEY.AGT_NO;
        }
        if (WS_AGT_FLG == 'N' 
            && CICMAGT.DATA.AGT_STS == 'N') {
            CEP.TRC(SCCGWA, "NOTFOUND ADD");
            B030_ADD_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_AGT_FLG == 'N' 
            && CICMAGT.DATA.AGT_STS == 'C') {
            CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
            CIRAGT.AGT_TYP = CICMAGT.DATA.AGT_TYP;
            CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
            WS_AGT_STS = 'C';
            CEP.TRC(SCCGWA, WS_AGT_STS);
            CIRAGT.AGT_STS = WS_AGT_STS;
            CEP.TRC(SCCGWA, CIRAGT.AGT_STS);
            T000_READ_CITAGT_UPD_INDX();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
            if (WS_AGT_FLG == 'Y') {
                CICMAGT.DATA.AGT_NO = CIRAGT.KEY.AGT_NO;
            }
            CEP.TRC(SCCGWA, "NOTFOUND 2");
            if (WS_AGT_FLG == 'N') {
                B030_ADD_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "RECORD FOUND");
        R000_CHECK_AGT_PRMR();
        if (pgmRtn) return;
        WS_AGT_TYP = CIRAGT.AGT_TYP;
        R000_CHECK_MX_AGT_TYP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
        CEP.TRC(SCCGWA, CIRAGT.CI_NO);
        CIRAGT.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
        CEP.TRC(SCCGWA, "BBBBB");
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        T000_DELETE_CITAGT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
        CEP.TRC(SCCGWA, "AAAAA-MODIFY");
        if (CICMAGT.DATA.AGT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_NO_NOT_GENERATE, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRAGT);
            CIRAGT.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
            T000_READ_CITAGT();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                CEP.TRC(SCCGWA, "AAAAA-MODIFY-NFND");
                R000_TRANS_DATA_TO_TBL();
                if (pgmRtn) return;
                T000_WRITE_CITAGT();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_EXIST, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
        if (CICMAGT.DATA.AGT_STS == 'C') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_GET_CI_TYP();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_MAGT();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.INP_AREA.BD_ECIF_SG0001.ECIF_OPTTYPE = '3';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        R000_RLTM_INFORM_ECIF();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRAGTO);
        IBS.init(SCCGWA, CIRAGTN);
        CEP.TRC(SCCGWA, CICMAGT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        if (CICMAGT.DATA.AGT_NO.trim().length() > 0) {
            CIRAGT.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
            CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
            CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
            CIRAGT.AGT_STS = 'N';
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
            CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
            CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
            CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_TYP);
            T000_READ_CITAGT_UPD();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_NOTFND, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CICMAGT.DATA.AGT_TYP = CIRAGT.AGT_TYP;
            CEP.TRC(SCCGWA, CIRAGT.AGT_TYP);
        } else {
            R000_CHECK_ENTY_STS();
            if (pgmRtn) return;
            CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
            CIRAGT.AGT_TYP = CICMAGT.DATA.AGT_TYP;
            CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
            CIRAGT.AGT_STS = 'N';
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
            T000_READ_CITAGT_UPD_INDX();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_NOTFND, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
        CIRAGT.AGT_STS = 'C';
        CIRAGT.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_CITAGT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.INP_AREA.BD_ECIF_SG0001.ECIF_OPTTYPE = '2';
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        R000_RLTM_INFORM_ECIF();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (CICMAGT.DATA.CI_NO.trim().length() > 0) {
            WS_CI_NO = CICMAGT.DATA.CI_NO;
            B080_10_CI_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            if (CICMAGT.DATA.ENTY_TYP != ' ' 
                && CICMAGT.DATA.ENTY_NO.trim().length() > 0) {
                if (CICMAGT.DATA.ENTY_TYP == '0') {
                    WS_CI_NO = CICMAGT.DATA.ENTY_NO;
                    B080_10_CI_BROWSE_PROC();
                    if (pgmRtn) return;
                } else {
                    B080_20_ENTY_BROWSE_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (CICMAGT.DATA.AGT_TYP.trim().length() > 0) {
                    B080_30_TYP_BROWSE_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B080_10_CI_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CIRAGT.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITAGT_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B080_20_ENTY_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICMAGT.DATA.ENTY_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
        T000_STARTBR_CITAGT_ENTY();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B080_30_TYP_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.AGT_TYP = CICMAGT.DATA.AGT_TYP;
        T000_STARTBR_CITAGT_TYP();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOAGTL);
        CICOAGTL.CI_NO = CIRAGT.CI_NO;
        CICOAGTL.AGT_NO = CIRAGT.KEY.AGT_NO;
        CICOAGTL.AGT_TYP = CIRAGT.AGT_TYP;
        CICOAGTL.ENTY_TYP = CIRAGT.KEY.ENTY_TYP;
        CICOAGTL.ENTY_NO = CIRAGT.KEY.ENTY_NO;
        CICOAGTL.AGT_LVL = CIRAGT.AGT_LVL;
        CICOAGTL.EFF_DATE = CIRAGT.EFF_DATE;
        CICOAGTL.EXP_DATE = CIRAGT.EXP_DATE;
        CICOAGTL.SGN_DATE = CIRAGT.SGN_DATE;
        CICOAGTL.AGT_STS = CIRAGT.AGT_STS;
        CICOAGTL.CHNL_NO = CIRAGT.SGN_CHNL;
        CICOAGTL.REMARK = CIRAGT.REMARK;
        CICOAGTL.TEL_NO = CIRAGT.TEL_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOAGTL);
        SCCMPAG.DATA_LEN = 347;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_RLTM_INFORM_ECIF() throws IOException,SQLException,Exception {
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CICMAGT.DATA.CI_NO;
        if (CICMAGT.DATA.ENTY_TYP != '0') {
            BPCPNHIS.INFO.AC = CICMAGT.DATA.ENTY_NO;
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 664;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRAGTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRAGTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        CIRAGT.CI_NO = CICMAGT.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        CIRAGT.KEY.AGT_NO = CICMAGT.DATA.AGT_NO;
        CIRAGT.AGT_TYP = CICMAGT.DATA.AGT_TYP;
        CIRAGT.KEY.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICMAGT.DATA.ENTY_NO;
        CEP.TRC(SCCGWA, CICMAGT.DATA.FRM_APP);
        CEP.TRC(SCCGWA, CICPAGT.FRM_APP);
        CIRAGT.FRM_APP = CICMAGT.DATA.FRM_APP;
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_LVL);
        CEP.TRC(SCCGWA, CICPAGT.AGT_LVL);
        CIRAGT.AGT_LVL = CICPAGT.AGT_LVL;
        if (CICMAGT.DATA.EFF_DATE == 0) {
            CIRAGT.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CIRAGT.EFF_DATE = CICMAGT.DATA.EFF_DATE;
        }
        if (CICMAGT.DATA.EXP_DATE == 0) {
            CIRAGT.EXP_DATE = K_EXP_DATE;
        } else {
            CIRAGT.EXP_DATE = CICMAGT.DATA.EXP_DATE;
        }
        if (CICMAGT.DATA.SGN_DATE == 0) {
            CIRAGT.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CIRAGT.SGN_DATE = CICMAGT.DATA.SGN_DATE;
        }
        CIRAGT.AGT_STS = 'N';
        if (CICMAGT.DATA.ORG_NO.trim().length() > 0) {
            CIRAGT.ORG_NO = CICMAGT.DATA.ORG_NO;
        } else {
            CIRAGT.ORG_NO = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            JIBS_tmp_int = CIRAGT.ORG_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) CIRAGT.ORG_NO = "0" + CIRAGT.ORG_NO;
        }
        CEP.TRC(SCCGWA, CICMAGT.DATA.ORG_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, CIRAGT.ORG_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (CICMAGT.DATA.SGN_CHNL.trim().length() > 0) {
            CIRAGT.SGN_CHNL = CICMAGT.DATA.SGN_CHNL;
        } else {
            CIRAGT.SGN_CHNL = SCCGWA.COMM_AREA.CHNL;
        }
        CEP.TRC(SCCGWA, CICMAGT.DATA.REMARK);
        CIRAGT.REMARK = CICMAGT.DATA.REMARK;
        CIRAGT.TEL_NO = CICMAGT.DATA.TEL_NO;
        CIRAGT.SGN_DAT = CICMAGT.DATA.SGN_DAT;
        if (CICMAGT.FUNC == 'A') {
            CIRAGT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.FRM_APP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_LVL);
    }
    public void R000_DATA_TRANS_TO_MAGT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT.DATA);
        CICMAGT.DATA.CI_NO = CIRAGT.CI_NO;
        CICMAGT.DATA.AGT_NO = CIRAGT.KEY.AGT_NO;
        CICMAGT.DATA.AGT_TYP = CIRAGT.AGT_TYP;
        CICMAGT.DATA.ENTY_TYP = CIRAGT.KEY.ENTY_TYP;
        CICMAGT.DATA.ENTY_NO = CIRAGT.KEY.ENTY_NO;
        CICMAGT.DATA.FRM_APP = CIRAGT.FRM_APP;
        CICMAGT.DATA.AGT_LVL = CIRAGT.AGT_LVL;
        CICMAGT.DATA.EFF_DATE = CIRAGT.EFF_DATE;
        CICMAGT.DATA.EXP_DATE = CIRAGT.EXP_DATE;
        CICMAGT.DATA.SGN_DATE = CIRAGT.SGN_DATE;
        CICMAGT.DATA.AGT_STS = CIRAGT.AGT_STS;
        CICMAGT.DATA.ORG_NO = CIRAGT.ORG_NO;
        CICMAGT.DATA.SGN_CHNL = CIRAGT.SGN_CHNL;
        CICMAGT.DATA.REMARK = CIRAGT.REMARK;
        CICMAGT.DATA.TEL_NO = CIRAGT.TEL_NO;
        CICMAGT.DATA.SGN_DAT = CIRAGT.SGN_DAT;
        CICMAGT.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICMAGT.DATA.CRT_TLR = CIRAGT.CRT_TLR;
        CICMAGT.DATA.CRT_DT = CIRAGT.CRT_DT;
        CICMAGT.DATA.CRT_BR = CIRAGT.CRT_BR;
        CEP.TRC(SCCGWA, CICMAGT.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CIRAGT.CI_NO);
        CEP.TRC(SCCGWA, CIRAGT.AGT_TYP);
        CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOAGTR);
        CICOAGTR.CI_NO = CICMAGT.DATA.CI_NO;
        CICOAGTR.AGT_NO = CICMAGT.DATA.AGT_NO;
        CICOAGTR.AGT_TYP = CICMAGT.DATA.AGT_TYP;
        CICOAGTR.ENTY_TYP = CICMAGT.DATA.ENTY_TYP;
        CICOAGTR.ENTY_NO = CICMAGT.DATA.ENTY_NO;
        CICOAGTR.FRM_APP = CICMAGT.DATA.FRM_APP;
        CICOAGTR.AGT_LVL = CICMAGT.DATA.AGT_LVL;
        CICOAGTR.EFF_DATE = CICMAGT.DATA.EFF_DATE;
        CEP.TRC(SCCGWA, CICOAGTR.EFF_DATE);
        CICOAGTR.EXP_DATE = CICMAGT.DATA.EXP_DATE;
        CICOAGTR.SGN_DATE = CICMAGT.DATA.SGN_DATE;
        CICOAGTR.AGT_STS = CICMAGT.DATA.AGT_STS;
        CICOAGTR.ORG_NO = CICMAGT.DATA.ORG_NO;
        CICOAGTR.SGN_CHNL = CICMAGT.DATA.SGN_CHNL;
        CEP.TRC(SCCGWA, CICOAGTR.SGN_CHNL);
        CICOAGTR.REMARK = CICMAGT.DATA.REMARK;
        CICOAGTR.TEL_NO = CICMAGT.DATA.TEL_NO;
        CICOAGTR.SGN_DAT = CICMAGT.DATA.SGN_DAT;
        CICOAGTR.CI_TYP = CICMAGT.DATA.CI_TYP;
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMAGT.FUNC == 'A' 
            || CICMAGT.FUNC == 'M' 
            || CICMAGT.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICOAGTR;
        SCCFMT.DATA_LEN = 553;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "STSW,CI_TYP";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.col = "STS,CI_NO,ENTY_TYP,FRM_APP";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITAGT_INDEX() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.where = "ENTY_NO = :CIRAGT.KEY.ENTY_NO "
            + "AND ENTY_TYP = :CIRAGT.KEY.ENTY_TYP "
            + "AND AGT_TYP = :CIRAGT.AGT_TYP "
            + "AND AGT_STS = :CIRAGT.AGT_STS";
        CITAGT_RD.fst = true;
        IBS.READ(SCCGWA, CIRAGT, this, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITJRL_FIRST() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        CITJRL_RD.where = "JCI_NO = :CIRJRL.KEY.JCI_NO";
        CITJRL_RD.fst = true;
        IBS.READ(SCCGWA, CIRJRL, this, CITJRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JRL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITJRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.WRITE(SCCGWA, CIRAGT, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITAGT_UPD() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.eqWhere = "AGT_NO , ENTY_TYP , ENTY_NO , AGT_STS";
        CITAGT_RD.upd = true;
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITAGT_UPD_INDX() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.eqWhere = "AGT_TYP, ENTY_TYP , ENTY_NO , AGT_STS";
        CITAGT_RD.upd = true;
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.REWRITE(SCCGWA, CIRAGT, CITAGT_RD);
    }
    public void T000_DELETE_CITAGT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRAGT.CI_NO);
        CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
        CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
        CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRAGT.AGT_TYP);
        CEP.TRC(SCCGWA, CIRAGT.AGT_STS);
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.where = "AGT_NO = :CIRAGT.KEY.AGT_NO";
        IBS.DELETE(SCCGWA, CIRAGT, this, CITAGT_RD);
    }
    public void T000_STARTBR_CITAGT_ENTY_B() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "ENTY_NO = :CIRAGT.KEY.ENTY_NO "
            + "OR ( ENTY_NO = :WS_ENTY_NO "
            + "AND AGT_TYP = :CIRAGT.AGT_TYP )";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_ENTY() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "ENTY_NO = :CIRAGT.KEY.ENTY_NO "
            + "AND ENTY_TYP = :CIRAGT.KEY.ENTY_TYP";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_ENTY_CI() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "ENTY_NO = :CIRAGT.KEY.ENTY_NO "
            + "AND ENTY_TYP = :CIRAGT.KEY.ENTY_TYP "
            + "AND AGT_STS = 'N'";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_CI() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "CI_NO = :CIRAGT.CI_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_NO() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "AGT_NO = :CIRAGT.KEY.AGT_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_TYP() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "AGT_TYP = :CIRAGT.AGT_TYP";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
    }
    public void S000_LINK_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI  ", CICSSCH);
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_CIZUAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GNL-AGT-NO", CICUAGT);
        if (CICUAGT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICUAGT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_NOT_DEF, CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO);
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
