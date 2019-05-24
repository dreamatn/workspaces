package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFWFHD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_OTS_TATH_CHK = "BP-F-OTS-TATH-CHK   ";
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    char WS_IN_FLG = ' ';
    String WS_CHNL_NO = " ";
    char WS_ATH_TYP = ' ';
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCFOTTA BPCFOTTA = new BPCFOTTA();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPCFWFHD BPCFWFHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFWFHD BPCFWFHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFWFHD = BPCFWFHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFWFHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCFOTTA);
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFWFHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFWFHD.TLR.trim().length() == 0 
            || BPCFWFHD.WF_NAME.trim().length() == 0 
            || BPCFWFHD.WF_Q_NAME.trim().length() == 0 
            || !IBS.isNumeric(BPCFWFHD.OPER_LVL+"")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FWFHD_TLR, BPCFWFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCFWFHD.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFWFHD.OPER_LVL > BPCFTLRQ.INFO.TMP_TX_LVL) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOW_TX_LVL, BPCFWFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_ATH_TYP = '0';
        WS_IN_FLG = 'F';
        WS_CHNL_NO = "IBS";
        IBS.init(SCCGWA, BPCFOTTA);
        BPCFOTTA.ASSTYP = 'T';
        BPCFOTTA.ASS_ID = BPCFWFHD.TLR;
        BPCFOTTA.ATH_TYP = WS_ATH_TYP;
        BPCFOTTA.IN_FLG = WS_IN_FLG;
        BPCFOTTA.SYS_MMO = WS_CHNL_NO;
        if (BPCFOTTA.TX_CD == null) BPCFOTTA.TX_CD = "";
        JIBS_tmp_int = BPCFOTTA.TX_CD.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPCFOTTA.TX_CD += " ";
        if (BPCFWFHD.WF_NAME == null) BPCFWFHD.WF_NAME = "";
        JIBS_tmp_int = BPCFWFHD.WF_NAME.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPCFWFHD.WF_NAME += " ";
        BPCFOTTA.TX_CD = BPCFWFHD.WF_NAME + BPCFOTTA.TX_CD.substring(5);
        if (BPCFOTTA.TX_CD == null) BPCFOTTA.TX_CD = "";
        JIBS_tmp_int = BPCFOTTA.TX_CD.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPCFOTTA.TX_CD += " ";
        if (BPCFWFHD.WF_Q_NAME == null) BPCFWFHD.WF_Q_NAME = "";
        JIBS_tmp_int = BPCFWFHD.WF_Q_NAME.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCFWFHD.WF_Q_NAME += " ";
        BPCFOTTA.TX_CD = BPCFOTTA.TX_CD.substring(0, 6 - 1) + BPCFWFHD.WF_Q_NAME + BPCFOTTA.TX_CD.substring(6 + 2 - 1);
        S000_CALL_BPZFOTTA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFOTTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_OTS_TATH_CHK, BPCFOTTA);
        if (BPCFOTTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFWFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFWFHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFWFHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFWFHD = ");
            CEP.TRC(SCCGWA, BPCFWFHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
