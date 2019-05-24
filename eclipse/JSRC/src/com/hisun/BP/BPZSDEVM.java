package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSDEVM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPZ08";
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_R_IDEV_MAINT = "BP-R-IDEV-MAINT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "DEVIATION MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHDEV";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String K_CALL_BP_P_INQ_ORG = "BP-P-INQ-ORG";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    BPZSDEVM_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSDEVM_WS_OUTPUT_DATA();
    BPZSDEVM_WS_KEY WS_KEY = new BPZSDEVM_WS_KEY();
    BPZSDEVM_WS_UPDATE_DATA WS_UPDATE_DATA = new BPZSDEVM_WS_UPDATE_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCDEVMO BPCDEVMO = new BPCDEVMO();
    BPCRDEVM BPCRDEVM = new BPCRDEVM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRIDEV BPRIDEV = new BPRIDEV();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHDEV BPCOHDEV = new BPCHDEV();
    BPCHDEV BPCNHDEV = new BPCHDEV();
    SCCGWA SCCGWA;
    BPCSDEVM BPCSDEVM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSDEVM BPCSDEVM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSDEVM = BPCSDEVM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSDEVM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSDEVM.RC);
        IBS.init(SCCGWA, BPRIDEV);
        IBS.init(SCCGWA, BPCPQORG);
        BPCSDEVM.RC.RC_AP = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MOVE_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSDEVM);
        if (BPCSDEVM.INFO.FUNC == 'Q'
            || BPCSDEVM.INFO.FUNC == 'A'
            || BPCSDEVM.INFO.FUNC == 'U'
            || BPCSDEVM.INFO.FUNC == 'C'
            || BPCSDEVM.INFO.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSDEVM.INFO.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCSDEVM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B300_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSDEVM);
        if (BPCSDEVM.INFO.FUNC == 'B') {
            if (BPCSDEVM.INFO.TYPE == ' ' 
                || (BPCSDEVM.INFO.BASE_TYP.trim().length() == 0 
                && BPCSDEVM.INFO.TENOR.equalsIgnoreCase("0"))) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1, BPCSDEVM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSDEVM.INFO.FUNC == 'A' 
            || BPCSDEVM.INFO.FUNC == 'Q' 
            || BPCSDEVM.INFO.FUNC == 'D' 
            || BPCSDEVM.INFO.FUNC == 'U' 
            || BPCSDEVM.INFO.FUNC == 'C') {
            if (BPCSDEVM.INFO.CCY.trim().length() == 0 
                || BPCSDEVM.INFO.BASE_TYP.trim().length() == 0 
                || BPCSDEVM.INFO.TENOR.equalsIgnoreCase("0")) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1, BPCSDEVM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSDEVM.INFO.TYPE);
        if (BPCSDEVM.INFO.FUNC == 'A' 
            || BPCSDEVM.INFO.FUNC == 'U') {
            CEP.TRC(SCCGWA, BPCSDEVM.INFO.BR);
            if (BPCSDEVM.INFO.BR != 0 
                || BPCSDEVM.INFO.BR != ' ') {
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = BPCSDEVM.INFO.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            if ((BPCSDEVM.INFO.DATA[WS_I-1].DEVIA == 0 
                && BPCSDEVM.INFO.DATA[WS_I-1].DEVIA != 0)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR, BPCSDEVM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "LOL");
        }
        for (WS_I = 1; WS_I <= 9; WS_I += 1) {
            WS_K = (short) (WS_I + 1);
            for (WS_J = WS_K; WS_J <= 10; WS_J += 1) {
                if (BPCSDEVM.INFO.DATA[WS_I-1].DEVIA == 0 
                    && BPCSDEVM.INFO.DATA[WS_J-1].DEVIA != 0) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR, BPCSDEVM.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPCSDEVM.INFO.DATA[WS_I-1].DEVIA != 0 
                    && BPCSDEVM.INFO.DATA[WS_J-1].DEVIA != 0 
                    && (BPCSDEVM.INFO.DATA[WS_I-1].DEVIA >= BPCSDEVM.INFO.DATA[WS_J-1].DEVIA)) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR, BPCSDEVM.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPCSDEVM.INFO.DATA[WS_I-1].DEVIA_AU != 0 
                    && BPCSDEVM.INFO.DATA[WS_J-1].DEVIA_AU != 0 
                    && (BPCSDEVM.INFO.DATA[WS_I-1].DEVIA_AU >= BPCSDEVM.INFO.DATA[WS_J-1].DEVIA_AU)) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR, BPCSDEVM.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSDEVM.INFO.FUNC == 'U') {
            WS_UPDATE_DATA.WS_SDEVM_TYPE = BPCSDEVM.INFO.TYPE;
            WS_UPDATE_DATA.WS_SDEVM_BR = BPCSDEVM.INFO.BR;
            WS_UPDATE_DATA.WS_SDEVM_CCY = BPCSDEVM.INFO.CCY;
            WS_UPDATE_DATA.WS_SDEVM_BASE_TYP = BPCSDEVM.INFO.BASE_TYP;
            WS_UPDATE_DATA.WS_SDEVM_TENOR = BPCSDEVM.INFO.TENOR;
            WS_UPDATE_DATA.WS_SDEVM_FMT = BPCSDEVM.INFO.FMT;
            WS_UPDATE_DATA.WS_SDEVM_CNT = BPCSDEVM.CNT;
            WS_UPDATE_DATA.WS_SDEVM_DATA[1-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[1-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[1-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[1-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[2-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[2-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[2-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[2-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[3-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[3-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[3-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[3-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[4-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[4-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[4-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[4-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[5-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[5-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[5-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[5-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[6-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[6-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[6-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[6-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[7-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[7-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[7-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[7-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[8-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[8-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[8-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[8-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[9-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[9-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[9-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[9-1].DEVIA_AU;
            WS_UPDATE_DATA.WS_SDEVM_DATA[10-1].WS_SDEVM_DEVIA = BPCSDEVM.INFO.DATA[10-1].DEVIA;
            WS_UPDATE_DATA.WS_SDEVM_DATA[10-1].WS_SDEVM_DEVIA_AU = BPCSDEVM.INFO.DATA[10-1].DEVIA_AU;
            IBS.init(SCCGWA, BPRIDEV);
            IBS.init(SCCGWA, BPCRDEVM);
            BPRIDEV.KEY.TYPE = BPCSDEVM.INFO.TYPE;
            BPRIDEV.KEY.BR = BPCSDEVM.INFO.BR;
            BPRIDEV.KEY.CCY = BPCSDEVM.INFO.CCY;
            BPRIDEV.KEY.BASE_TYP = BPCSDEVM.INFO.BASE_TYP;
            BPRIDEV.KEY.TENOR = BPCSDEVM.INFO.TENOR;
            BPCRDEVM.INFO.FUNC = 'I';
            S000_CALL_BPZRDEVM();
            if (pgmRtn) return;
            if (WS_UPDATE_DATA.WS_SDEVM_TYPE == BPRIDEV.KEY.TYPE 
                && WS_UPDATE_DATA.WS_SDEVM_BR == BPRIDEV.KEY.BR 
                && WS_UPDATE_DATA.WS_SDEVM_CCY.equalsIgnoreCase(BPRIDEV.KEY.CCY) 
                && WS_UPDATE_DATA.WS_SDEVM_BASE_TYP.equalsIgnoreCase(BPRIDEV.KEY.BASE_TYP) 
                && WS_UPDATE_DATA.WS_SDEVM_TENOR.equalsIgnoreCase(BPRIDEV.KEY.TENOR) 
                && WS_UPDATE_DATA.WS_SDEVM_FMT == BPRIDEV.FORMAT 
                && WS_UPDATE_DATA.WS_SDEVM_CNT == BPRIDEV.CNT 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[1-1].WS_SDEVM_DEVIA == BPRIDEV.DEV1 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[1-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL1 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[2-1].WS_SDEVM_DEVIA == BPRIDEV.DEV2 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[2-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL2 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[3-1].WS_SDEVM_DEVIA == BPRIDEV.DEV3 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[3-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL3 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[4-1].WS_SDEVM_DEVIA == BPRIDEV.DEV4 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[4-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL4 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[5-1].WS_SDEVM_DEVIA == BPRIDEV.DEV5 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[5-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL5 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[6-1].WS_SDEVM_DEVIA == BPRIDEV.DEV6 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[6-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL6 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[7-1].WS_SDEVM_DEVIA == BPRIDEV.DEV7 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[7-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL7 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[8-1].WS_SDEVM_DEVIA == BPRIDEV.DEV8 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[8-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL8 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[9-1].WS_SDEVM_DEVIA == BPRIDEV.DEV9 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[9-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL9 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[10-1].WS_SDEVM_DEVIA == BPRIDEV.DEV10 
                && WS_UPDATE_DATA.WS_SDEVM_DATA[10-1].WS_SDEVM_DEVIA_AU == BPRIDEV.AL10) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG, BPCSDEVM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MOVE_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIDEV);
        BPRIDEV.KEY.TYPE = BPCSDEVM.INFO.TYPE;
        BPRIDEV.KEY.BR = BPCSDEVM.INFO.BR;
        BPRIDEV.KEY.CCY = BPCSDEVM.INFO.CCY;
        BPRIDEV.KEY.BASE_TYP = BPCSDEVM.INFO.BASE_TYP;
        BPRIDEV.KEY.TENOR = BPCSDEVM.INFO.TENOR;
        BPRIDEV.FORMAT = BPCSDEVM.INFO.FMT;
        BPRIDEV.CNT = BPCSDEVM.CNT;
        CEP.TRC(SCCGWA, BPRIDEV.CNT);
        BPRIDEV.DEV1 = BPCSDEVM.INFO.DATA[1-1].DEVIA;
        BPRIDEV.AL1 = BPCSDEVM.INFO.DATA[1-1].DEVIA_AU;
        BPRIDEV.DEV2 = BPCSDEVM.INFO.DATA[2-1].DEVIA;
        BPRIDEV.AL2 = BPCSDEVM.INFO.DATA[2-1].DEVIA_AU;
        BPRIDEV.DEV3 = BPCSDEVM.INFO.DATA[3-1].DEVIA;
        BPRIDEV.AL3 = BPCSDEVM.INFO.DATA[3-1].DEVIA_AU;
        BPRIDEV.DEV4 = BPCSDEVM.INFO.DATA[4-1].DEVIA;
        BPRIDEV.AL4 = BPCSDEVM.INFO.DATA[4-1].DEVIA_AU;
        BPRIDEV.DEV5 = BPCSDEVM.INFO.DATA[5-1].DEVIA;
        BPRIDEV.AL5 = BPCSDEVM.INFO.DATA[5-1].DEVIA_AU;
        BPRIDEV.DEV6 = BPCSDEVM.INFO.DATA[6-1].DEVIA;
        BPRIDEV.AL6 = BPCSDEVM.INFO.DATA[6-1].DEVIA_AU;
        BPRIDEV.DEV7 = BPCSDEVM.INFO.DATA[7-1].DEVIA;
        BPRIDEV.AL7 = BPCSDEVM.INFO.DATA[7-1].DEVIA_AU;
        BPRIDEV.DEV8 = BPCSDEVM.INFO.DATA[8-1].DEVIA;
        BPRIDEV.AL8 = BPCSDEVM.INFO.DATA[8-1].DEVIA_AU;
        BPRIDEV.DEV9 = BPCSDEVM.INFO.DATA[9-1].DEVIA;
        BPRIDEV.AL9 = BPCSDEVM.INFO.DATA[9-1].DEVIA_AU;
        BPRIDEV.DEV10 = BPCSDEVM.INFO.DATA[10-1].DEVIA;
        BPRIDEV.AL10 = BPCSDEVM.INFO.DATA[10-1].DEVIA_AU;
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDEVM);
        if (BPCSDEVM.INFO.FUNC == 'Q'
            || BPCSDEVM.INFO.FUNC == 'C') {
            BPCRDEVM.INFO.FUNC = 'I';
        } else if (BPCSDEVM.INFO.FUNC == 'A') {
            BPCRDEVM.INFO.FUNC = 'A';
        } else if (BPCSDEVM.INFO.FUNC == 'U'
            || BPCSDEVM.INFO.FUNC == 'D') {
            BPCRDEVM.INFO.FUNC = 'R';
        }
        S000_CALL_BPZRDEVM();
        if (pgmRtn) return;
        R000_CHECK_RETURN_1();
        if (pgmRtn) return;
        if (BPCSDEVM.INFO.FUNC == 'D' 
            || BPCSDEVM.INFO.FUNC == 'A' 
            || BPCSDEVM.INFO.FUNC == 'U') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSDEVM.INFO.FUNC == 'U' 
            || BPCSDEVM.INFO.FUNC == 'D') {
            if (BPCSDEVM.INFO.FUNC == 'D') {
                BPCRDEVM.INFO.FUNC = 'D';
                S000_CALL_BPZRDEVM();
                if (pgmRtn) return;
            }
            if (BPCSDEVM.INFO.FUNC == 'U') {
                B200_MOVE_INPUT_DATA();
                if (pgmRtn) return;
                BPCRDEVM.INFO.FUNC = 'U';
                S000_CALL_BPZRDEVM();
                if (pgmRtn) return;
            }
        }
        if (BPCSDEVM.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDEVM);
        BPCRDEVM.INFO.FUNC = 'B';
        BPCRDEVM.INFO.OPT_A = 'S';
        S000_CALL_BPZRDEVM();
        if (pgmRtn) return;
        if (BPCSDEVM.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCRDEVM);
            BPCRDEVM.INFO.FUNC = 'B';
            BPCRDEVM.INFO.OPT_A = 'N';
            S000_CALL_BPZRDEVM();
            if (pgmRtn) return;
            if (BPCRDEVM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPCSDEVM.OUTPUT_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCRDEVM);
        BPCRDEVM.INFO.FUNC = 'B';
        BPCRDEVM.INFO.OPT_A = 'E';
        S000_CALL_BPZRDEVM();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 18;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_RDEVM_TYPE = BPRIDEV.KEY.TYPE;
            WS_OUTPUT_DATA.WS_RDEVM_BR = BPRIDEV.KEY.BR;
            WS_OUTPUT_DATA.WS_RDEVM_CCY = BPRIDEV.KEY.CCY;
            WS_OUTPUT_DATA.WS_RDEVM_BASE_TYP = BPRIDEV.KEY.BASE_TYP;
            WS_OUTPUT_DATA.WS_RDEVM_TENOR = BPRIDEV.KEY.TENOR;
            WS_OUTPUT_DATA.WS_RDEVM_FORMAT = BPRIDEV.FORMAT;
            CEP.TRC(SCCGWA, "BPZSDEVMFZ");
            CEP.TRC(SCCGWA, BPRIDEV.KEY.TYPE);
            CEP.TRC(SCCGWA, BPRIDEV.KEY.BR);
            CEP.TRC(SCCGWA, BPRIDEV.KEY.CCY);
            CEP.TRC(SCCGWA, BPRIDEV.KEY.BASE_TYP);
            CEP.TRC(SCCGWA, BPRIDEV.FORMAT);
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 18;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B300_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        BPCSDEVM.INFO.TYPE = BPRIDEV.KEY.TYPE;
        BPCSDEVM.INFO.BR = BPRIDEV.KEY.BR;
        BPCSDEVM.INFO.CCY = BPRIDEV.KEY.CCY;
        BPCSDEVM.INFO.BASE_TYP = BPRIDEV.KEY.BASE_TYP;
        BPCSDEVM.INFO.TENOR = BPRIDEV.KEY.TENOR;
        BPCSDEVM.INFO.FMT = BPRIDEV.FORMAT;
        BPCSDEVM.INFO.DATA[1-1].DEVIA = BPRIDEV.DEV1;
        BPCSDEVM.INFO.DATA[1-1].DEVIA_AU = BPRIDEV.AL1;
        BPCSDEVM.INFO.DATA[2-1].DEVIA = BPRIDEV.DEV2;
        BPCSDEVM.INFO.DATA[2-1].DEVIA_AU = BPRIDEV.AL2;
        BPCSDEVM.INFO.DATA[3-1].DEVIA = BPRIDEV.DEV3;
        BPCSDEVM.INFO.DATA[3-1].DEVIA_AU = BPRIDEV.AL3;
        BPCSDEVM.INFO.DATA[4-1].DEVIA = BPRIDEV.DEV4;
        BPCSDEVM.INFO.DATA[4-1].DEVIA_AU = BPRIDEV.AL4;
        BPCSDEVM.INFO.DATA[5-1].DEVIA = BPRIDEV.DEV5;
        BPCSDEVM.INFO.DATA[5-1].DEVIA_AU = BPRIDEV.AL5;
        BPCSDEVM.INFO.DATA[6-1].DEVIA = BPRIDEV.DEV6;
        BPCSDEVM.INFO.DATA[6-1].DEVIA_AU = BPRIDEV.AL6;
        BPCSDEVM.INFO.DATA[7-1].DEVIA = BPRIDEV.DEV7;
        BPCSDEVM.INFO.DATA[7-1].DEVIA_AU = BPRIDEV.AL7;
        BPCSDEVM.INFO.DATA[8-1].DEVIA = BPRIDEV.DEV8;
        BPCSDEVM.INFO.DATA[8-1].DEVIA_AU = BPRIDEV.AL8;
        BPCSDEVM.INFO.DATA[9-1].DEVIA = BPRIDEV.DEV9;
        BPCSDEVM.INFO.DATA[9-1].DEVIA_AU = BPRIDEV.AL9;
        BPCSDEVM.INFO.DATA[10-1].DEVIA = BPRIDEV.DEV10;
        BPCSDEVM.INFO.DATA[10-1].DEVIA_AU = BPRIDEV.AL10;
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCDEVMO);
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRIDEV.DEV1);
        CEP.TRC(SCCGWA, BPRIDEV.AL1);
        CEP.TRC(SCCGWA, BPRIDEV.DEV2);
        CEP.TRC(SCCGWA, BPRIDEV.AL2);
        CEP.TRC(SCCGWA, BPCDEVMO.TYPE);
        CEP.TRC(SCCGWA, BPCDEVMO.BR);
        CEP.TRC(SCCGWA, BPCDEVMO.CCY);
        CEP.TRC(SCCGWA, BPCDEVMO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCDEVMO.FMT);
        CEP.TRC(SCCGWA, "ISHERE");
        CEP.TRC(SCCGWA, BPCDEVMO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCDEVMO;
        SCCFMT.DATA_LEN = 250;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        BPCDEVMO.TYPE = BPRIDEV.KEY.TYPE;
        BPCDEVMO.BR = BPRIDEV.KEY.BR;
        BPCDEVMO.CCY = BPRIDEV.KEY.CCY;
        BPCDEVMO.BASE_TYP = BPRIDEV.KEY.BASE_TYP;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPRIDEV.KEY.BASE_TYP;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCDEVMO.BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCDEVMO.TENOR = BPRIDEV.KEY.TENOR;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = BPRIDEV.KEY.TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCDEVMO.TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCDEVMO.FMT = BPRIDEV.FORMAT;
        BPCDEVMO.DATA[1-1].DEVIA = BPRIDEV.DEV1;
        BPCDEVMO.DATA[1-1].DEVIA_AU = BPRIDEV.AL1;
        BPCDEVMO.DATA[2-1].DEVIA = BPRIDEV.DEV2;
        BPCDEVMO.DATA[2-1].DEVIA_AU = BPRIDEV.AL2;
        BPCDEVMO.DATA[3-1].DEVIA = BPRIDEV.DEV3;
        BPCDEVMO.DATA[3-1].DEVIA_AU = BPRIDEV.AL3;
        BPCDEVMO.DATA[4-1].DEVIA = BPRIDEV.DEV4;
        BPCDEVMO.DATA[4-1].DEVIA_AU = BPRIDEV.AL4;
        BPCDEVMO.DATA[5-1].DEVIA = BPRIDEV.DEV5;
        BPCDEVMO.DATA[5-1].DEVIA_AU = BPRIDEV.AL5;
        BPCDEVMO.DATA[6-1].DEVIA = BPRIDEV.DEV6;
        BPCDEVMO.DATA[6-1].DEVIA_AU = BPRIDEV.AL6;
        BPCDEVMO.DATA[7-1].DEVIA = BPRIDEV.DEV7;
        BPCDEVMO.DATA[7-1].DEVIA_AU = BPRIDEV.AL7;
        BPCDEVMO.DATA[8-1].DEVIA = BPRIDEV.DEV8;
        BPCDEVMO.DATA[8-1].DEVIA_AU = BPRIDEV.AL8;
        BPCDEVMO.DATA[9-1].DEVIA = BPRIDEV.DEV9;
        BPCDEVMO.DATA[9-1].DEVIA_AU = BPRIDEV.AL9;
        BPCDEVMO.DATA[10-1].DEVIA = BPRIDEV.DEV10;
        BPCDEVMO.DATA[10-1].DEVIA_AU = BPRIDEV.AL10;
    }
    public void R000_CHECK_RETURN_1() throws IOException,SQLException,Exception {
        if (BPCRDEVM.RETURN_INFO == 'N') {
            if (BPCSDEVM.INFO.FUNC == 'Q' 
                || BPCSDEVM.INFO.FUNC == 'U' 
                || BPCSDEVM.INFO.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND, BPCSDEVM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSDEVM.INFO.FUNC == 'C') {
                BPCSDEVM.EXIST_FLG = 'N';
            }
        }
        if (BPCRDEVM.RETURN_INFO == 'F') {
            if (BPCSDEVM.INFO.FUNC == 'C') {
                BPCSDEVM.EXIST_FLG = 'Y';
            }
        }
        if (BPCRDEVM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_RECORD_EXIST, BPCSDEVM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSDEVM.INFO.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHDEV);
            BPCOHDEV.TYPE = BPRIDEV.KEY.TYPE;
            BPCOHDEV.BR = BPRIDEV.KEY.BR;
            BPCOHDEV.CCY = BPRIDEV.KEY.CCY;
            BPCOHDEV.BASE_TYP = BPRIDEV.KEY.BASE_TYP;
            BPCOHDEV.TENOR = BPRIDEV.KEY.TENOR;
            BPCOHDEV.FMT = BPRIDEV.FORMAT;
            BPCOHDEV.DATA[1-1].DEVIA = BPRIDEV.DEV1;
            BPCOHDEV.DATA[1-1].DEVIA_AU = BPRIDEV.AL1;
            BPCOHDEV.DATA[2-1].DEVIA = BPRIDEV.DEV2;
            BPCOHDEV.DATA[2-1].DEVIA_AU = BPRIDEV.AL2;
            BPCOHDEV.DATA[3-1].DEVIA = BPRIDEV.DEV3;
            BPCOHDEV.DATA[3-1].DEVIA_AU = BPRIDEV.AL3;
            BPCOHDEV.DATA[4-1].DEVIA = BPRIDEV.DEV4;
            BPCOHDEV.DATA[4-1].DEVIA_AU = BPRIDEV.AL4;
            BPCOHDEV.DATA[5-1].DEVIA = BPRIDEV.DEV5;
            BPCOHDEV.DATA[5-1].DEVIA_AU = BPRIDEV.AL5;
            BPCOHDEV.DATA[6-1].DEVIA = BPRIDEV.DEV6;
            BPCOHDEV.DATA[6-1].DEVIA_AU = BPRIDEV.AL6;
            BPCOHDEV.DATA[7-1].DEVIA = BPRIDEV.DEV7;
            BPCOHDEV.DATA[7-1].DEVIA_AU = BPRIDEV.AL7;
            BPCOHDEV.DATA[8-1].DEVIA = BPRIDEV.DEV8;
            BPCOHDEV.DATA[8-1].DEVIA_AU = BPRIDEV.AL8;
            BPCOHDEV.DATA[9-1].DEVIA = BPRIDEV.DEV9;
            BPCOHDEV.DATA[9-1].DEVIA_AU = BPRIDEV.AL9;
            BPCOHDEV.DATA[10-1].DEVIA = BPRIDEV.DEV10;
            BPCOHDEV.DATA[10-1].DEVIA_AU = BPRIDEV.AL10;
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSDEVM.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSDEVM.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSDEVM.INFO.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        WS_KEY.WS_KEY_TYPE = BPCSDEVM.INFO.TYPE;
        WS_KEY.WS_KEY_BR = BPCSDEVM.INFO.BR;
        WS_KEY.WS_KEY_CCY = BPCSDEVM.INFO.CCY;
        WS_KEY.WS_KEY_BASE_TYP = BPCSDEVM.INFO.BASE_TYP;
        WS_KEY.WS_KEY_TENOR = BPCSDEVM.INFO.TENOR;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_KEY);
        CEP.TRC(SCCGWA, "WS-KEY  ");
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.REF_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, "JRNNO   ");
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        if (BPCSDEVM.INFO.FUNC == 'U') {
            CEP.TRC(SCCGWA, "BPCOHDEV");
            CEP.TRC(SCCGWA, BPCOHDEV);
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHDEV;
            IBS.init(SCCGWA, BPCNHDEV);
            BPCNHDEV.TYPE = BPCSDEVM.INFO.TYPE;
            BPCNHDEV.BR = BPCSDEVM.INFO.BR;
            BPCNHDEV.CCY = BPCSDEVM.INFO.CCY;
            BPCNHDEV.BASE_TYP = BPCSDEVM.INFO.BASE_TYP;
            BPCNHDEV.TENOR = BPCSDEVM.INFO.TENOR;
            BPCNHDEV.FMT = BPCSDEVM.INFO.FMT;
            for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                BPCNHDEV.DATA[WS_I-1].DEVIA = BPCSDEVM.INFO.DATA[WS_I-1].DEVIA;
                BPCNHDEV.DATA[WS_I-1].DEVIA_AU = BPCSDEVM.INFO.DATA[WS_I-1].DEVIA_AU;
            }
            CEP.TRC(SCCGWA, "BPCNHDEV");
            CEP.TRC(SCCGWA, BPCNHDEV);
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHDEV;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSDEVM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_QPCD_MAIN, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSDEVM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRDEVM() throws IOException,SQLException,Exception {
        BPCRDEVM.INFO.POINTER = BPRIDEV;
        IBS.CALLCPN(SCCGWA, K_R_IDEV_MAINT, BPCRDEVM);
        CEP.TRC(SCCGWA, BPCRDEVM.RC.RC_CODE);
        if (BPCRDEVM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDEVM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSDEVM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BP_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSDEVM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSDEVM.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
