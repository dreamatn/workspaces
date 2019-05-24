package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCHHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_F_OTS_TATH_CHK = "BP-F-OTS-TATH-CHK   ";
    char WS_IN_FLG = ' ';
    String WS_CHNL_NO = " ";
    char WS_ATH_TYP = ' ';
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCFOTTA BPCFOTTA = new BPCFOTTA();
    SCCGWA SCCGWA;
    BPCFCHHD BPCFCHHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, BPCFCHHD BPCFCHHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCHHD = BPCFCHHD;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "ENTER BPZFCHHD");
        CEP.TRC(SCCGWA, BPCFCHHD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
        A000_INIT_PROC();
        if (pgmRtn) return;
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("TLR") 
            && SCCGWA.COMM_AREA.CHNL.trim().length() > 0) {
            CEP.TRC(SCCGWA, "ENTER B000");
            CEP.TRC(SCCGWA, BPCFCHHD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
            CEP.TRC(SCCGWA, SCCGAPV.TYPE);
            if (SCCGAPV.TYPE != 'A' 
                && SCCGAPV.TYPE != 'L' 
                && SCCGAPV.TYPE != 'R') {
                B000_MAIN_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "BEFOR END");
        CEP.TRC(SCCGWA, BPCFCHHD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
        CEP.TRC(SCCGWA, "BPZFCHHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFOR INIT");
        CEP.TRC(SCCGWA, BPCFCHHD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, BPCFOTTA);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFCHHD.RC);
        CEP.TRC(SCCGWA, "AFTER INIT");
        CEP.TRC(SCCGWA, BPCFCHHD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCHHD);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_ATH_TYP = '0';
        WS_IN_FLG = 'I';
        WS_CHNL_NO = "IBS";
        IBS.init(SCCGWA, BPCFOTTA);
        BPCFOTTA.ASSTYP = 'C';
        BPCFOTTA.ASS_ID = SCCGWA.COMM_AREA.CHNL;
        BPCFOTTA.ATH_TYP = WS_ATH_TYP;
        BPCFOTTA.IN_FLG = WS_IN_FLG;
        BPCFOTTA.SYS_MMO = WS_CHNL_NO;
        BPCFOTTA.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        S000_CALL_BPZFOTTA();
        if (pgmRtn) return;
        if (BPCFOTTA.PRIV_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_NOT_ALLOW, BPCFCHHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFOTTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_OTS_TATH_CHK, BPCFOTTA);
        if (BPCFOTTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCHHD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFCHHD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCHHD = ");
            CEP.TRC(SCCGWA, BPCFCHHD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
