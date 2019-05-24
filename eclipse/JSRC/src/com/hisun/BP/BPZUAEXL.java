package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUAEXL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_LEN = 0;
    String WS_OTH = " ";
    BPZUAEXL_REDEFINES5 REDEFINES5 = new BPZUAEXL_REDEFINES5();
    BPZUAEXL_WS_BPRCNGL[] WS_BPRCNGL = new BPZUAEXL_WS_BPRCNGL[100];
    char WS_STOP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCEXLGL BPCEXLGL = new BPCEXLGL();
    BPRCNGL BPRCNGL = new BPRCNGL();
    BPCRCNGL BPCRCNGL = new BPCRCNGL();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPCEXCHK BPCEXCHK = new BPCEXCHK();
    BPREXUPF BPREXUPF = new BPREXUPF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCEXAPV BPCEXAPV;
    public BPZUAEXL() {
        for (int i=0;i<100;i++) WS_BPRCNGL[i] = new BPZUAEXL_WS_BPRCNGL();
    }
    public void MP(SCCGWA SCCGWA, BPCEXAPV BPCEXAPV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXAPV = BPCEXAPV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUAEXL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXLGL);
        IBS.init(SCCGWA, BPRCNGL);
        IBS.init(SCCGWA, BPCRCNGL);
        IBS.init(SCCGWA, BPCRMUPD);
        IBS.init(SCCGWA, BPCEXCHK);
        IBS.init(SCCGWA, BPREXUPF);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCEXAPV.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BRW_EXUPF_FOR_CHK();
        if (pgmRtn) return;
        B030_CREATE_CNGL_REC();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (!BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("04")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_NOT_CNGL, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXAPV.BATCH_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_EXUPF_FOR_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMUPD);
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
        while (WS_STOP != 'Y') {
            BPCRMUPD.INFO.OPT = 'N';
            S000_CALL_BPZRMUPD();
            if (pgmRtn) return;
            if (BPCRMUPD.RETURN_INFO == 'N') {
                WS_STOP = 'Y';
            } else {
                if (BPCRMUPD.RETURN_INFO == 'F') {
                    if (BPREXUPF.KEY.BATCH_NO.equalsIgnoreCase(BPCEXAPV.BATCH_NO)) {
                        B021_CHK_REC_VALID();
                        if (pgmRtn) return;
                    } else {
                        WS_STOP = 'Y';
                    }
                } else {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_READ_BPTEXUPF_ERR, BPCEXAPV.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCRMUPD);
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'E';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
    }
    public void B021_CHK_REC_VALID() throws IOException,SQLException,Exception {
        WS_CNT += 1;
        IBS.init(SCCGWA, BPCEXCHK);
        BPCEXCHK.EXCEL_TYPE = BPCEXAPV.EXCEL_TYPE;
        BPCEXCHK.EXCEL_DATA = BPREXUPF.RECORD;
        S000_CALL_BPZUCEXL();
        if (pgmRtn) return;
        if (BPCEXCHK.DATA_FLG == '0') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPRCNGL);
            IBS.init(SCCGWA, BPCEXLGL);
            WS_LEN = 53;
            CEP.TRC(SCCGWA, WS_LEN);
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_LEN), BPCEXLGL);
            BPRCNGL.KEY.CNTR_TYPE = BPCEXLGL.KEY.CNTR_TYPE;
            BPRCNGL.KEY.BOOK_FLG = BPCEXLGL.KEY.BOOK_FLG;
            BPRCNGL.KEY.BR = BPCEXLGL.KEY.BR;
            CEP.TRC(SCCGWA, BPCEXLGL.KEY.OTH.PROD_TYPE);
            CEP.TRC(SCCGWA, BPCEXLGL.KEY.OTH.AC_STATUS);
            WS_OTH = " ";
            REDEFINES5.WS_ACCMD_PROD_TYPE = BPCEXLGL.KEY.OTH.PROD_TYPE;
            WS_OTH = IBS.CLS2CPY(SCCGWA, REDEFINES5);
            REDEFINES5.WS_ACCMD_AC_STATUS = BPCEXLGL.KEY.OTH.AC_STATUS;
            WS_OTH = IBS.CLS2CPY(SCCGWA, REDEFINES5);
            BPRCNGL.KEY.OTH = WS_OTH;
            CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
            BPRCNGL.MSTNO = BPCEXLGL.TXT.MSTNO;
            BPRCNGL.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
            BPRCNGL.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRCNGL.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPRCNGL.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRCNGL.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCNGL);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BPRCNGL[WS_CNT-1]);
        }
    }
    public void B030_CREATE_CNGL_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRCNGL);
        for (WS_CNT1 = 1; WS_CNT1 <= WS_CNT; WS_CNT1 += 1) {
            IBS.init(SCCGWA, BPCRCNGL);
            BPCRCNGL.FUNC = 'A';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BPRCNGL[WS_CNT1-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCNGL);
            BPCRCNGL.DAT_PTR = BPRCNGL;
            S000_CALL_BPZRCNGL();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-CNGL", BPCRCNGL);
        if (BPCRCNGL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCEXL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-CNGL-CHK", BPCEXCHK);
        if (BPCEXCHK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-UPDATA", BPCRMUPD);
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
