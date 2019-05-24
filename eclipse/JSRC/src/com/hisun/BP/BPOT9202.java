package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9202 {
    String K_FMT_CD = "BP751";
    String K_RTBK_AP_TYPE = "UPBKRT";
    String K_TD_SERV_CODE = "BSPTD11";
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    short WS_FLD_NO = 0;
    char WS_STOP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOEHOL BPCOEHOL = new BPCOEHOL();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPREXUPF BPREXUPF = new BPREXUPF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    BPCEXAPV BPCEXAPV = new BPCEXAPV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9202_AWA_9202 BPB9202_AWA_9202;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9202 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9202_AWA_9202>");
        BPB9202_AWA_9202 = (BPB9202_AWA_9202) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCEXAPV);
        if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("01")) {
            BPCEXAPV.EXCEL_TYPE = "01";
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL BPZUEXC");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S020_CALL_BPZUEXC();
            CEP.TRC(SCCGWA, "END   CALL BPZUEXC");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                S000_ERR_MSG_PROC();
            }
        } else if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("02")) {
            BPCEXAPV.EXCEL_TYPE = "02";
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL BPZEXHOW");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S020_CALL_BPZEXHOW();
            CEP.TRC(SCCGWA, "END   CALL BPZEXHOW");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                S000_ERR_MSG_PROC();
            }
        } else if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("03")
            || BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("X3")) {
            if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("03")) {
                BPCEXAPV.EXCEL_TYPE = "03";
            } else {
                BPCEXAPV.EXCEL_TYPE = "X3";
            }
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL AIZEODEW");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S030_CALL_AIZEODEW();
            CEP.TRC(SCCGWA, "END   CALL AIZEODEW");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                WS_ERR_INF = BPCEXAPV.RC.ERR_INF;
                S000_ERR_MSG_PROC();
            }
        } else if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("04")) {
            BPCEXAPV.EXCEL_TYPE = "04";
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL BPZUAEXL");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S040_CALL_BPZUAEXL();
            CEP.TRC(SCCGWA, "END   CALL BPZUAEXL");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                S000_ERR_MSG_PROC();
            }
        } else if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("05")) {
            BPCEXAPV.EXCEL_TYPE = "05";
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL BPZEXURT");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S050_CALL_BPZEXURT();
            CEP.TRC(SCCGWA, "END   CALL BPZEXURT");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                S000_ERR_MSG_PROC();
            }
        } else if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("08")) {
            BPCEXAPV.EXCEL_TYPE = "08";
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL COZUXPYE");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S080_CALL_COZUXPYE();
            CEP.TRC(SCCGWA, "END   CALL COZUXPYE");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                S000_ERR_MSG_PROC();
            }
        } else if (BPB9202_AWA_9202.UP_TYPE.equalsIgnoreCase("09")) {
            BPCEXAPV.EXCEL_TYPE = "09";
            BPCEXAPV.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
            CEP.TRC(SCCGWA, "BEGIN CALL COZUXREF");
            CEP.TRC(SCCGWA, BPCEXAPV);
            S080_CALL_COZUXREF();
            CEP.TRC(SCCGWA, "END   CALL COZUXREF");
            CEP.TRC(SCCGWA, BPCEXAPV.RC);
            if (BPCEXAPV.RC.RC_CODE > 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEXAPV.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLOAD_TYPE_ERR;
            WS_FLD_NO = BPB9202_AWA_9202.UP_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCEXAPV.RC.RC_CODE == 0 
            && !BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("X3")) {
            B200_UPD_BPTEXUPF_PROC();
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9202_AWA_9202.BAT_NO);
        CEP.TRC(SCCGWA, BPB9202_AWA_9202.UP_TYPE);
        if (BPB9202_AWA_9202.BAT_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY;
            WS_FLD_NO = BPB9202_AWA_9202.BAT_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB9202_AWA_9202.UP_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLOAD_TYPE_ERR;
            WS_FLD_NO = BPB9202_AWA_9202.UP_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_UPD_BPTEXUPF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'S';
        BPCRMUPD.INFO.BRW_OPT = 'U';
        BPREXUPF.KEY.BATCH_NO = BPB9202_AWA_9202.BAT_NO;
        CEP.TRC(SCCGWA, BPREXUPF.KEY.BATCH_NO);
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        S000_CALL_BPZRMUPD();
        WS_STOP = 'N';
        while (WS_STOP != 'Y') {
            BPCRMUPD.INFO.FUNC = 'B';
            BPCRMUPD.INFO.OPT = 'N';
            S000_CALL_BPZRMUPD();
            if (BPCRMUPD.RETURN_INFO == 'N') {
                WS_STOP = 'Y';
            } else {
                IBS.init(SCCGWA, BPCRMUPD);
                BPCRMUPD.INFO.FUNC = 'U';
                BPREXUPF.APPV_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPREXUPF.APPV_FLAG = 'A';
                BPCRMUPD.INFO.POINTER = BPREXUPF;
                BPCRMUPD.INFO.LEN = 183;
                S000_CALL_BPZRMUPD();
            }
        }
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'E';
        S000_CALL_BPZRMUPD();
    }
    public void B300_START_BSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'F';
        SCCBSPS.SERV_CODE = K_TD_SERV_CODE;
        CEP.TRC(SCCGWA, SCCBSPS.SERV_CODE);
        S000_CALL_SCZBSPS();
        CEP.TRC(SCCGWA, SCCBSPS.CHK_FLG);
        if (SCCBSPS.CHK_FLG != 'Y') {
            B300_01_START_TD_BSP();
        }
    }
    public void B300_01_START_TD_BSP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'G';
        SCCBSPS.SERV_CODE = K_TD_SERV_CODE;
        CEP.TRC(SCCGWA, SCCBSPS.SERV_CODE);
        S000_CALL_SCZBSPS();
        IBS.init(SCCGWA, SCCBSP);
        SCCBSP.SERV_CODE = K_TD_SERV_CODE;
        CEP.TRC(SCCGWA, SCCBSP.SERV_CODE);
        S000_CALL_SCZOBSP();
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S020_CALL_BPZUEXC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EXCEL-UPD", BPCEXAPV);
    }
    public void S020_CALL_BPZEXHOW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-HOL-UPD", BPCEXAPV);
    }
    public void S030_CALL_AIZEODEW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-EXCEL-ODE-AUTH", BPCEXAPV);
    }
    public void S040_CALL_BPZUAEXL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-CNGL-AUH", BPCEXAPV);
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-UPDATA", BPCRMUPD);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S050_CALL_BPZEXURT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-RATE-UPD", BPCEXAPV);
    }
    public void S080_CALL_COZUXPYE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CO-U-EXCEL-PAYEE-UPD", BPCEXAPV);
    }
    public void S080_CALL_COZUXREF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CO-U-EXCEL-REF-UPD", BPCEXAPV);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
