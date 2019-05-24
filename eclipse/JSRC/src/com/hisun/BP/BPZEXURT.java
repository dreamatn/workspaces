package com.hisun.BP;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZEXURT {
    BPZEXURT_WS_EX_RATE_DATA WS_EX_RATE_DATA;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_REC_CNT = 0;
    int WS_RATE_CNT = 0;
    List<BPZEXURT_WS_EX_RATE_DATA> WS_EX_RATE_DATA = new ArrayList<BPZEXURT_WS_EX_RATE_DATA>();
    char WS_STOP = ' ';
    char WS_CHK_RATE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSURTE BPCSURTE = new BPCSURTE();
    BPCEXCHK BPCEXCHK = new BPCEXCHK();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPREXUPF BPREXUPF = new BPREXUPF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCEXAPV BPCEXAPV;
    public void MP(SCCGWA SCCGWA, BPCEXAPV BPCEXAPV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXAPV = BPCEXAPV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZEXURT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXAPV.RC);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_BRW_EXUPF_FOR_CHK();
        if (pgmRtn) return;
        if (WS_CHK_RATE == 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_RATE_DATA_ERR, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_RATE_CNT > 0) {
            B300_UPDATE_RATE_TABLE();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCEXAPV.BATCH_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_BRW_EXUPF_FOR_CHK() throws IOException,SQLException,Exception {
        WS_CHK_RATE = 'P';
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
        WS_RATE_CNT = 0;
        WS_EX_RATE_DATA = new BPZEXURT_WS_EX_RATE_DATA();
        WS_EX_RATE_DATA.add(WS_EX_RATE_DATA);
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
                        B200_01_CHK_REC_VALID();
                        if (pgmRtn) return;
                        if (WS_CHK_RATE == 'F') {
                            WS_STOP = 'Y';
                        }
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
    public void B200_01_CHK_REC_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK);
        BPCEXCHK.EXCEL_TYPE = BPCEXAPV.EXCEL_TYPE;
        BPCEXCHK.EXCEL_DATA = BPREXUPF.RECORD;
        CEP.TRC(SCCGWA, BPCEXCHK.EXCEL_DATA);
        S000_CALL_BPZEXCRT();
        if (pgmRtn) return;
        if (BPCEXCHK.DATA_FLG == '0') {
            WS_CHK_RATE = 'F';
        } else {
            WS_EX_RATE_DATA = new BPZEXURT_WS_EX_RATE_DATA();
            WS_EX_RATE_DATA.add(WS_EX_RATE_DATA);
            WS_RATE_CNT = WS_RATE_CNT + 1;
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD, WS_EX_RATE_DATA);
        }
        CEP.TRC(SCCGWA, BPCEXCHK.DATA_FLG);
    }
    public void B300_UPDATE_RATE_TABLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RATE_CNT);
        for (WS_I = 1; WS_I <= WS_RATE_CNT; WS_I += 1) {
            IBS.init(SCCGWA, BPCSURTE);
            CEP.TRC(SCCGWA, WS_EX_RATE_DATA.get(WS_I-1).WS_RATE_TYPE);
            CEP.TRC(SCCGWA, WS_EX_RATE_DATA.get(WS_I-1).WS_CCY);
            CEP.TRC(SCCGWA, WS_EX_RATE_DATA.get(WS_I-1).WS_TENOR);
            CEP.TRC(SCCGWA, WS_EX_RATE_DATA.get(WS_I-1).WS_EFF_DATE);
            CEP.TRC(SCCGWA, WS_EX_RATE_DATA.get(WS_I-1).WS_RATE);
            BPCSURTE.BASE_TYP = WS_EX_RATE_DATA.get(WS_I-1).WS_RATE_TYPE;
            BPCSURTE.CCY = WS_EX_RATE_DATA.get(WS_I-1).WS_CCY;
            BPCSURTE.TENOR = WS_EX_RATE_DATA.get(WS_I-1).WS_TENOR;
            BPCSURTE.EFF_DT = WS_EX_RATE_DATA.get(WS_I-1).WS_EFF_DATE;
            BPCSURTE.RATE = WS_EX_RATE_DATA.get(WS_I-1).WS_RATE;
            S000_CALL_BPZSURTE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZEXCRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-RATE-CHK", BPCEXCHK);
        if (BPCEXCHK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSURTE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-UPD-RATE", BPCSURTE);
        CEP.TRC(SCCGWA, BPCSURTE.RC);
        if (BPCSURTE.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSURTE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTEXUPF, BPCRMUPD);
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
            CEP.TRC(SCCGWA, " BPCEXAPV = ");
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
