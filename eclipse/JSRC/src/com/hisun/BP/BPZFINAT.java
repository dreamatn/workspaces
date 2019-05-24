package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFINAT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_F_TLR_ATH_CHK = "BP-F-TLR-ATH-CHK    ";
    char WS_IN_FLG = ' ';
    String WS_CHNL_NO = " ";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCFTLAT BPCFTLAT = new BPCFTLAT();
    SCCGWA SCCGWA;
    BPCFINAT BPCFINAT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFINAT BPCFINAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFINAT = BPCFINAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFINAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTXIF);
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPCFTLAT);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFINAT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFINAT);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_IN_FLG = 'O';
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPRTXIF);
        BPCRTXIF.INFO.FUNC = 'Q';
        BPRTXIF.KEY.IN_FLG = WS_IN_FLG;
        BPRTXIF.KEY.SYS_MMO = SCCGWA.COMM_AREA.CHNL;
        BPRTXIF.KEY.TX_CD = SCCGWA.COMM_AREA.SERV_CODE;
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            WS_CHNL_NO = "IBS";
            IBS.init(SCCGWA, BPCFTLAT);
            BPCFTLAT.TLR = BPCFINAT.TLR;
            BPCFTLAT.TLRC_FLG = BPCFINAT.TLRC_FLG;
            BPCFTLAT.TLRC_PSW = BPCFINAT.TLRC_PSW;
            BPCFTLAT.TLRK_PSW = BPCFINAT.TLRK_PSW;
            BPCFTLAT.AUTH_LVL = BPCFINAT.AUTH_LVL;
            BPCFTLAT.CHNL_ID = WS_CHNL_NO;
            BPCFTLAT.SRV_ID = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            S000_CALL_BPZFTLAT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFTLAT);
            BPCFTLAT.TLR = BPCFINAT.TLR;
            BPCFTLAT.TLRC_FLG = BPCFINAT.TLRC_FLG;
            BPCFTLAT.TLRC_PSW = BPCFINAT.TLRC_PSW;
            BPCFTLAT.TLRK_PSW = BPCFINAT.TLRK_PSW;
            BPCFTLAT.AUTH_LVL = BPCFINAT.AUTH_LVL;
            BPCFTLAT.CHNL_ID = SCCGWA.COMM_AREA.CHNL;
            BPCFTLAT.SRV_ID = SCCGWA.COMM_AREA.SERV_CODE;
            S000_CALL_BPZFTLAT();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_ATH_CHK, BPCFTLAT);
        CEP.TRC(SCCGWA, BPCFTLAT.RC);
        if (BPCFTLAT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLAT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFINAT.RC);
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFINAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFINAT.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFINAT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFINAT = ");
            CEP.TRC(SCCGWA, BPCFINAT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
