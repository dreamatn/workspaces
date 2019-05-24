package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLHD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_F_OTS_TATH_CHK = "BP-F-OTS-TATH-CHK   ";
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    BPZFTLHD_WS_ERR_MSG WS_ERR_MSG = new BPZFTLHD_WS_ERR_MSG();
    char WS_MSG_TYPE = ' ';
    BPZFTLHD_WS_LVL WS_LVL = new BPZFTLHD_WS_LVL();
    String WS_CHNL_NO = " ";
    char WS_ATH_TYP = ' ';
    char WS_TLR_TX_LVL = ' ';
    char WS_TLR_FIN_SRV_FLG = ' ';
    char WS_TLR_ADM_SRV_FLG = ' ';
    char WS_TLR_PRIV_SRV_FLG = ' ';
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTXIF BPRTXIF = new BPRTXIF();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCFOTTA BPCFOTTA = new BPCFOTTA();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPCFTLHD BPCFTLHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFTLHD BPCFTLHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLHD = BPCFTLHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTXIF);
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPCFOTTA);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFTLHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLHD);
        if (BPCFTLHD.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FTLHD_TLR, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFTLHD.CHNL_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FTLHD_CHNID, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFTLHD.SRV_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FTLHD_SVID, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCFTLHD.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        WS_TLR_TX_LVL = BPCFTLRQ.INFO.TMP_TX_LVL;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        WS_TLR_FIN_SRV_FLG = BPCFTLRQ.INFO.TLR_STSW.substring(6 - 1, 6 + 1 - 1).charAt(0);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        WS_TLR_ADM_SRV_FLG = BPCFTLRQ.INFO.TLR_STSW.substring(7 - 1, 7 + 1 - 1).charAt(0);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        WS_TLR_PRIV_SRV_FLG = BPCFTLRQ.INFO.TLR_STSW.substring(5 - 1, 5 + 1 - 1).charAt(0);
        if (BPCFTLRQ.INFO.TLR_TYP != 'T') {
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPRTXIF);
        BPCRTXIF.INFO.FUNC = 'Q';
        WS_CHNL_NO = BPCFTLHD.CHNL_ID;
        if (WS_CHNL_NO.equalsIgnoreCase("IBS")) {
            BPRTXIF.KEY.IN_FLG = 'I';
        } else {
            BPRTXIF.KEY.IN_FLG = 'O';
        }
        BPRTXIF.KEY.SYS_MMO = BPCFTLHD.CHNL_ID;
        BPRTXIF.KEY.TX_CD = BPCFTLHD.SRV_ID;
        CEP.TRC(SCCGWA, BPRTXIF.KEY.IN_FLG);
        CEP.TRC(SCCGWA, BPRTXIF.KEY.SYS_MMO);
        CEP.TRC(SCCGWA, BPRTXIF.KEY.TX_CD);
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXIF_NOTFND, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRTXIF.STS == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXIF_INV, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BBBBBB");
        CEP.TRC(SCCGWA, BPRTXIF.TX_LVL);
        CEP.TRC(SCCGWA, WS_TLR_TX_LVL);
        CEP.TRC(SCCGWA, "AAAAAA");
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        if (BPRTXIF.STSW.substring(0, 1).equalsIgnoreCase("1") 
            && !BPRTXIF.STSW.substring(0, 1).equalsIgnoreCase(WS_TLR_FIN_SRV_FLG+"")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FIN_SRV_ERR, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DDDDDD");
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        if (BPRTXIF.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && !BPRTXIF.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase(WS_TLR_ADM_SRV_FLG+"")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ADM_SRV_ERR, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        if (BPRTXIF.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPRTXIF.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(WS_TLR_PRIV_SRV_FLG+"")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRIV_SRV_ERR, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRTXIF.SATH_FLG == 'N') {
            Z_RET();
            if (pgmRtn) return;
        }
        WS_ATH_TYP = '0';
        CEP.TRC(SCCGWA, "EEEEEE");
        IBS.init(SCCGWA, BPCFOTTA);
        BPCFOTTA.ASSTYP = 'T';
        BPCFOTTA.ASS_ID = BPCFTLHD.TLR;
        BPCFOTTA.ATH_TYP = WS_ATH_TYP;
        BPCFOTTA.IN_FLG = BPRTXIF.KEY.IN_FLG;
        BPCFOTTA.SYS_MMO = BPRTXIF.KEY.SYS_MMO;
        BPCFOTTA.TX_CD = BPRTXIF.KEY.TX_CD;
        S000_CALL_BPZFOTTA();
        if (pgmRtn) return;
        BPCFOTTA.PRIV_FLG = 'Y';
        if (BPCFOTTA.PRIV_FLG == 'N') {
            CEP.TRC(SCCGWA, "AAAAAA");
            CEP.TRC(SCCGWA, "BP1628");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OPER_PRIV_NEXIST, BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFOTTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_OTS_TATH_CHK, BPCFOTTA);
        if (BPCFOTTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TXIF, BPCRTXIF);
        CEP.TRC(SCCGWA, BPCRTXIF.RC.RC_CODE);
        CEP.TRC(SCCGWA, "CGBYWSRTXIF");
        if (BPCRTXIF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = JIBS_tmp_str[0];
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.LVL.LVL1 = (char) WS_LVL.WS_LVL1;
        SCCMSG.LVL.LVL2 = (char) WS_LVL.WS_LVL2;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLHD = ");
            CEP.TRC(SCCGWA, BPCFTLHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
