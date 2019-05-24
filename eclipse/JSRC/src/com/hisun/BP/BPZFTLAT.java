package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLAT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_F_OTS_TATH_CHK = "BP-F-OTS-TATH-CHK   ";
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_F_C_PSW_MAINTAIN = "BP-F-C-PSW-MAINTAIN ";
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String WS_CHNL_NO = " ";
    char WS_ATH_TYP = ' ';
    char WS_TLR_AUTH_LVL = ' ';
    char WS_TLR_SIGN_STS = ' ';
    char WS_TLR_AUTH_RGN = ' ';
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCFOTTA BPCFOTTA = new BPCFOTTA();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCFCPSW BPCFCPSW = new BPCFCPSW();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    SCCGWA SCCGWA;
    BPCFTLAT BPCFTLAT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCFTLAT BPCFTLAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLAT = BPCFTLAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, BPRTXIF);
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPCFOTTA);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFTLAT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLAT);
        if (BPCFTLAT.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FTLAT_TLR, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(BPCFTLAT.AUTH_LVL+"")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FTLAT_AUTHLVL, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPRTXIF);
        BPCRTXIF.INFO.FUNC = 'Q';
        WS_CHNL_NO = BPCFTLAT.CHNL_ID;
        if (WS_CHNL_NO.equalsIgnoreCase("IBS")) {
            BPRTXIF.KEY.IN_FLG = 'I';
        } else {
            BPRTXIF.KEY.IN_FLG = 'O';
        }
        BPRTXIF.KEY.SYS_MMO = BPCFTLAT.CHNL_ID;
        BPRTXIF.KEY.TX_CD = BPCFTLAT.SRV_ID;
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXIF_NOTFND, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRTXIF.STS == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXIF_INV, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCFTLAT.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        WS_TLR_SIGN_STS = BPCFTLRQ.INFO.SIGN_STS;
        WS_TLR_AUTH_LVL = BPCFTLRQ.INFO.TMP_ATH_LVL;
        if (WS_TLR_SIGN_STS != 'O') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_TLR_SIGNOFF, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.SIGN_DT != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_TLR_SIGNOFF, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFTLAT.AUTH_LVL > WS_TLR_AUTH_LVL) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOW_AUTH_LVL, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TLR_AUTH_RGN = BPCFTLRQ.INFO.ATH_RGN;
        CEP.TRC(SCCGWA, WS_TLR_AUTH_RGN);
        if (WS_TLR_AUTH_RGN == '0') {
            if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_RGN_IN_BR, BPCFTLAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (WS_TLR_AUTH_RGN == '1') {
            if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                && BPCFTLRQ.INFO.NEW_BR != BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_RGN_BR_OSUB, BPCFTLAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (WS_TLR_AUTH_RGN == '2') {
            if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BR1 = BPCFTLRQ.INFO.NEW_BR;
                BPCPRGST.BR2 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPRGST();
                if (pgmRtn) return;
                if (BPCPRGST.LVL_RELATION != 'A') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_RGN_OUTSIDE, BPCFTLAT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_RGN_NVALID, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFTLAT.TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (BPRTXIF.SELF_ATH_FLG == 'N' 
            && BPCFTLAT.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SELFGR_NOT_ALLOW, BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRTXIF.SATH_FLG == 'N') {
            Z_RET();
            if (pgmRtn) return;
        }
        WS_ATH_TYP = '1';
        IBS.init(SCCGWA, BPCFOTTA);
        BPCFOTTA.ASSTYP = 'T';
        BPCFOTTA.ASS_ID = BPCFTLAT.TLR;
        BPCFOTTA.ATH_TYP = WS_ATH_TYP;
        BPCFOTTA.IN_FLG = BPRTXIF.KEY.IN_FLG;
        BPCFOTTA.SYS_MMO = BPRTXIF.KEY.SYS_MMO;
        BPCFOTTA.TX_CD = BPRTXIF.KEY.TX_CD;
        CEP.TRC(SCCGWA, BPCFOTTA);
        S000_CALL_BPZFOTTA();
        if (pgmRtn) return;
        if (BPCFOTTA.PRIV_FLG == 'N') {
        }
    }
    public void S000_CALL_BPZFOTTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_OTS_TATH_CHK, BPCFOTTA);
        if (BPCFOTTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TXIF, BPCRTXIF);
        if (BPCRTXIF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_C_PSW_MAINTAIN, BPCFCPSW);
        if (BPCFCPSW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCPSW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFKPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_K_PSW_MAINTAIN, BPCFKPSW);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLAT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLAT = ");
            CEP.TRC(SCCGWA, BPCFTLAT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
