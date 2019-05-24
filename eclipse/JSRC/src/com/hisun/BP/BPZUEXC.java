package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUEXC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_CNT = 20;
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    short WS_I = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_FWD_TENOR = " ";
    double WS_QTP_NEW = 0;
    double WS_QTP_OLD = 0;
    short WS_READ_CNT = 0;
    String WS_ERR_MSG = " ";
    int WS_REC_CNT = 0;
    char WS_STOP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCEXCR BPCEXCR = new BPCEXCR();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPCMT BPCMT = new BPCMT();
    BPREXUPF BPREXUPF = new BPREXUPF();
    SCCGWA SCCGWA;
    BPCEXAPV BPCEXAPV;
    public void MP(SCCGWA SCCGWA, BPCEXAPV BPCEXAPV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXAPV = BPCEXAPV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUEXC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXAPV.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEXAPV);
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_BRW_EXUPF_FOR_CHK();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "EEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        CEP.TRC(SCCGWA, BPCEXAPV.RC);
        if (!BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("01")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UPLOAD_TYPE_ERR, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXAPV.BATCH_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_BRW_EXUPF_FOR_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPREXUPF.KEY.BATCH_NO = BPCEXAPV.BATCH_NO;
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'S';
        BPREXUPF.KEY.BATCH_NO = BPCEXAPV.BATCH_NO;
        CEP.TRC(SCCGWA, BPCEXAPV.BATCH_NO);
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        WS_STOP = 'N';
        WS_REC_CNT = 0;
        while (WS_STOP != 'Y') {
            BPCRMUPD.INFO.FUNC = 'B';
            BPCRMUPD.INFO.OPT = 'N';
            S000_CALL_BPZRMUPD();
            if (pgmRtn) return;
            if (BPCRMUPD.RETURN_INFO == 'N') {
                WS_STOP = 'Y';
            } else {
                if (BPCRMUPD.RETURN_INFO == 'F') {
                    if (BPREXUPF.KEY.BATCH_NO.equalsIgnoreCase(BPCEXAPV.BATCH_NO)) {
                        WS_REC_CNT = WS_REC_CNT + 1;
                        IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD, BPCEXCR);
                        if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
                        JIBS_tmp_int = BPREXUPF.RECORD.length();
                        for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
                        CEP.TRC(SCCGWA, BPREXUPF.RECORD.substring(0, 50));
                        if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
                        JIBS_tmp_int = BPREXUPF.RECORD.length();
                        for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
                        CEP.TRC(SCCGWA, BPREXUPF.RECORD.substring(50 - 1, 50 + 50 - 1));
                        CEP.TRC(SCCGWA, BPCEXCR);
                        IBS.init(SCCGWA, BPCMT);
                        BPCMT.DATA.FUNC = 'A';
                        BPCMT.DATA.EXR_TYPE = BPCEXCR.EXR_TYP;
                        BPCMT.DATA.EFF_DT = BPCEXCR.EFF_DT;
                        BPCMT.DATA.EFF_TM = BPCEXCR.EFF_TM;
                        BPCMT.DATA.CCY_INFO[1-1].CCY = BPCEXCR.CCY;
                        BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR = BPCEXCR.FWD_TENOR;
                        BPCMT.DATA.CCY_INFO[1-1].FX_BUY = BPCEXCR.FX_BUY;
                        BPCMT.DATA.CCY_INFO[1-1].FX_SELL = BPCEXCR.FX_SELL;
                        BPCMT.DATA.CCY_INFO[1-1].CS_BUY = BPCEXCR.CS_BUY;
                        BPCMT.DATA.CCY_INFO[1-1].CS_SELL = BPCEXCR.CS_SELL;
                        BPCMT.DATA.CCY_INFO[1-1].MID_RAT = BPCEXCR.LOC_MID;
                        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].FX_BUY);
                        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].FX_SELL);
                        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CS_BUY);
                        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CS_SELL);
                        S00_CALL_BPZMT();
                        if (pgmRtn) return;
                    } else {
                        WS_STOP = 'Y';
                    }
                } else {
                    WS_STOP = 'Y';
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_READ_BPTEXUPF_ERR, BPCEXAPV.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'E';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT);
        CEP.TRC(SCCGWA, BPCMT.RC);
        if (BPCMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAIN_BPTEXUPF, BPCRMUPD);
        CEP.TRC(SCCGWA, BPCRMUPD.RC);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCEXAPV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCEXAPV = ");
            CEP.TRC(SCCGWA, BPCEXAPV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
