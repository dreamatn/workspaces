package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFINHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_F_TLR_HDL_CHK = "BP-F-TLR-HDL-CHK    ";
    char WS_IN_FLG = ' ';
    String WS_CHNL_NO = " ";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCFTLHD BPCFTLHD = new BPCFTLHD();
    SCCGWA SCCGWA;
    BPCFINHD BPCFINHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, BPCFINHD BPCFINHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFINHD = BPCFINHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        if (SCCGAPV.TYPE != 'A' 
            && SCCGAPV.TYPE != 'L' 
            && SCCGAPV.TYPE != 'R') {
            B000_MAIN_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BPZFINHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, BPRTXIF);
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPCFTLHD);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFINHD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFINHD);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_IN_FLG = 'O';
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPRTXIF);
        BPCRTXIF.INFO.FUNC = 'Q';
        BPRTXIF.KEY.IN_FLG = WS_IN_FLG;
        BPRTXIF.KEY.SYS_MMO = SCCGWA.COMM_AREA.CHNL;
        BPRTXIF.KEY.TX_CD = SCCGWA.COMM_AREA.SERV_CODE;
        CEP.TRC(SCCGWA, BPRTXIF.KEY.IN_FLG);
        CEP.TRC(SCCGWA, BPRTXIF.KEY.SYS_MMO);
        CEP.TRC(SCCGWA, BPRTXIF.KEY.TX_CD);
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            WS_CHNL_NO = "IBS";
            IBS.init(SCCGWA, BPCFTLHD);
            BPCFTLHD.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCFTLHD.CHNL_ID = WS_CHNL_NO;
            BPCFTLHD.SRV_ID = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CEP.TRC(SCCGWA, BPRTXIF.KEY.TX_CD);
            S000_CALL_BPZFTLHD();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFTLHD);
            BPCFTLHD.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCFTLHD.CHNL_ID = SCCGWA.COMM_AREA.CHNL;
            BPCFTLHD.SRV_ID = SCCGWA.COMM_AREA.SERV_CODE;
            S000_CALL_BPZFTLHD();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_HDL_CHK, BPCFTLHD);
        if (BPCFTLHD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLHD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFINHD.RC);
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFINHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFINHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFINHD = ");
            CEP.TRC(SCCGWA, BPCFINHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
