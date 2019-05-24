package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPLOSS {
    DBParm BPTLOSS_RD;
    brParm BPTLOSS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "BP811";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    char WS_OUT = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCO1820_OPT_1820 BPCO1820_OPT_1820 = new BPCO1820_OPT_1820();
    BPCO1821_OPT_1821 BPCO1821_OPT_1821 = new BPCO1821_OPT_1821();
    BPCO1830_OPT_1830 BPCO1830_OPT_1830 = new BPCO1830_OPT_1830();
    int WS_STA_DT = 0;
    int WS_END_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPLOSS BPCPLOSS;
    public void MP(SCCGWA SCCGWA, BPCPLOSS BPCPLOSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPLOSS = BPCPLOSS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPLOSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPLOSS.RC);
        WS_OUT = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_NO);
        if (BPCPLOSS.INFO.FUNC == 'I') {
            if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("1")) {
                if (BPCPLOSS.DATA_INFO.LOS_NO.trim().length() == 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                }
            }
            if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("2")) {
                if (BPCPLOSS.DATA_INFO.AC.trim().length() == 0 
                    || BPCPLOSS.DATA_INFO.STS == ' ') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                }
            }
        }
        if (BPCPLOSS.DATA_INFO.STA_DT != 0) {
            if (BPCPLOSS.DATA_INFO.STA_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DT_MORE_THAN_AC_DT);
            }
        }
        if (BPCPLOSS.DATA_INFO.END_DT != 0) {
            if (BPCPLOSS.DATA_INFO.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DT_MORE_THAN_AC_DT);
            }
        }
        if (BPCPLOSS.DATA_INFO.STA_DT != 0 
            && BPCPLOSS.DATA_INFO.END_DT != 0) {
            if (BPCPLOSS.DATA_INFO.STA_DT > BPCPLOSS.DATA_INFO.STA_DT) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OPENDT_GT_CLODT);
            }
        }
        if (BPCPLOSS.DATA_INFO.OPEN_DT != 0) {
            if (BPCPLOSS.DATA_INFO.OPEN_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DT_MORE_THAN_AC_DT);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPLOSS.INFO.FUNC);
        if (BPCPLOSS.INFO.FUNC == 'I') {
            B020_INQUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCPLOSS.INFO.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCPLOSS.INFO.FUNC == 'R') {
            B021_BROWSE_PROC();
            if (pgmRtn) return;
        }
        B030_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B020_INQUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        T000_READ_BPTLOSS();
        if (pgmRtn) return;
        if (BPCPLOSS.RETURN_INFO == 'F') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            } else {
                B060_02_DATA_OUTPUT_FMT();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOSS_NOTFND, BPCPLOSS.RC);
        }
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        T000_STARTBR_BPTLOSS();
        if (pgmRtn) return;
        T000_READNEXT_BPTLOSS();
        if (pgmRtn) return;
        if (BPCPLOSS.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCPLOSS.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRLOSS.BILL_TYP);
            if (BPRLOSS.BILL_TYP != ' ') {
                WS_OUT = 'N';
            } else {
                WS_OUT = 'Y';
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.CI_CNM.trim().length() > 0) {
                    if (!BPRLOSS.CI_CNM.equalsIgnoreCase(BPCPLOSS.DATA_INFO.CI_CNM)) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.BV_TYP != ' ') {
                    if (BPRLOSS.BV_TYP != BPCPLOSS.DATA_INFO.BV_TYP) {
                        CEP.TRC(SCCGWA, "2");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.BV_NO.trim().length() > 0) {
                    if (!BPRLOSS.BV_NO.equalsIgnoreCase(BPCPLOSS.DATA_INFO.BV_NO)) {
                        CEP.TRC(SCCGWA, "3");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.AC.trim().length() > 0) {
                    if (!BPRLOSS.AC.equalsIgnoreCase(BPCPLOSS.DATA_INFO.AC)) {
                        CEP.TRC(SCCGWA, "3");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.LOS_NO.trim().length() > 0) {
                    if (!BPRLOSS.KEY.LOS_NO.equalsIgnoreCase(BPCPLOSS.DATA_INFO.LOS_NO)) {
                        CEP.TRC(SCCGWA, "4");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.ID_TYP.trim().length() > 0) {
                    if (!BPRLOSS.ID_TYP.equalsIgnoreCase(BPCPLOSS.DATA_INFO.ID_TYP)) {
                        CEP.TRC(SCCGWA, "5");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.ID_NO.trim().length() > 0) {
                    if (!BPRLOSS.ID_NO.equalsIgnoreCase(BPCPLOSS.DATA_INFO.ID_NO)) {
                        CEP.TRC(SCCGWA, "6");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.LOS_ORG != 0) {
                    if (BPRLOSS.LOS_ORG != BPCPLOSS.DATA_INFO.LOS_ORG) {
                        CEP.TRC(SCCGWA, "7");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.STS != ' ') {
                    if (BPRLOSS.STS != BPCPLOSS.DATA_INFO.STS) {
                        CEP.TRC(SCCGWA, "8");
                        WS_OUT = 'N';
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_OUT);
            if (WS_OUT == 'Y') {
                B060_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTLOSS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTLOSS();
        if (pgmRtn) return;
    }
    public void B021_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        T000_STARTBR_BPTLOSS();
        if (pgmRtn) return;
        T000_READNEXT_BPTLOSS();
        if (pgmRtn) return;
        if (BPCPLOSS.RETURN_INFO == 'F') {
            B060_02_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCPLOSS.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.STS);
            CEP.TRC(SCCGWA, BPRLOSS.STS);
            CEP.TRC(SCCGWA, BPRLOSS.BILL_TYP);
            if (BPRLOSS.BILL_TYP == ' ') {
                WS_OUT = 'N';
            } else {
                WS_OUT = 'Y';
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.STS != ' ') {
                    if (BPRLOSS.STS != BPCPLOSS.DATA_INFO.STS) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.OPEN_BR != 0) {
                    if (BPRLOSS.OPEN_BR != BPCPLOSS.DATA_INFO.OPEN_BR) {
                        CEP.TRC(SCCGWA, "OPEN-BR");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.OPEN_DT != 0) {
                    if (BPRLOSS.OPEN_DT != BPCPLOSS.DATA_INFO.OPEN_DT) {
                        CEP.TRC(SCCGWA, "OPEN-DT");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.GET_NM.trim().length() > 0) {
                    if (!BPRLOSS.GET_NM.equalsIgnoreCase(BPCPLOSS.DATA_INFO.GET_NM)) {
                        CEP.TRC(SCCGWA, "LOSS-GET-NM");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.AC.trim().length() > 0) {
                    if (!BPRLOSS.AC.equalsIgnoreCase(BPCPLOSS.DATA_INFO.AC)) {
                        CEP.TRC(SCCGWA, "LOSS-AC");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.LOS_NO.trim().length() > 0) {
                    if (!BPRLOSS.KEY.LOS_NO.equalsIgnoreCase(BPCPLOSS.DATA_INFO.LOS_NO)) {
                        CEP.TRC(SCCGWA, "LOSS-LOS-NO");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.BILL_TYP != ' ') {
                    if (BPRLOSS.BILL_TYP != BPCPLOSS.DATA_INFO.BILL_TYP) {
                        CEP.TRC(SCCGWA, "PLOSS-BILL-TYP");
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPLOSS.DATA_INFO.BILL_NO.trim().length() > 0) {
                    if (!BPRLOSS.BILL_NO.equalsIgnoreCase(BPCPLOSS.DATA_INFO.BILL_NO)) {
                        CEP.TRC(SCCGWA, "PLOSS-BILL-NO");
                        WS_OUT = 'N';
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_OUT);
            if (WS_OUT == 'Y') {
                B060_03_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTLOSS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTLOSS();
        if (pgmRtn) return;
    }
    public void B030_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        BPCPLOSS.DATA_INFO.LOS_NO = BPRLOSS.KEY.LOS_NO;
        BPCPLOSS.DATA_INFO.AC = BPRLOSS.AC;
        BPCPLOSS.DATA_INFO.AC_TYPE = BPRLOSS.AC_TYPE;
        BPCPLOSS.DATA_INFO.STS = BPRLOSS.STS;
        BPCPLOSS.DATA_INFO.GET_AC_NO = BPRLOSS.GET_AC_NO;
        BPCPLOSS.DATA_INFO.GET_BR = BPRLOSS.GET_BR;
        BPCPLOSS.DATA_INFO.BV_NO = BPRLOSS.BV_NO;
        BPCPLOSS.DATA_INFO.NEW_BV_NO = BPRLOSS.NEW_BV_NO;
        BPCPLOSS.DATA_INFO.BV_TYP = BPRLOSS.BV_TYP;
        BPCPLOSS.DATA_INFO.BV_CODE = BPRLOSS.BV_CODE;
        BPCPLOSS.DATA_INFO.DEP_NO = BPRLOSS.DEP_NO;
        BPCPLOSS.DATA_INFO.OPEN_BR = BPRLOSS.OPEN_BR;
        BPCPLOSS.DATA_INFO.OPEN_AMT = BPRLOSS.OPEN_AMT;
        BPCPLOSS.DATA_INFO.BILL_TYP = BPRLOSS.BILL_TYP;
        BPCPLOSS.DATA_INFO.BILL_NO = BPRLOSS.BILL_NO;
        BPCPLOSS.DATA_INFO.SUP_BILL_NO = BPRLOSS.SUP_BILL_NO;
        BPCPLOSS.DATA_INFO.BILL_BR = BPRLOSS.BILL_BR;
        BPCPLOSS.DATA_INFO.LOS_RMK = BPRLOSS.LOS_RMK;
        BPCPLOSS.DATA_INFO.OTH_NM = BPRLOSS.OTH_NM;
        BPCPLOSS.DATA_INFO.OTH_ID_TYP = BPRLOSS.OTH_ID_TYP;
        BPCPLOSS.DATA_INFO.OTH_ID_NO = BPRLOSS.OTH_ID_NO;
        BPCPLOSS.DATA_INFO.LOS_NM = BPRLOSS.LOS_NM;
        BPCPLOSS.DATA_INFO.LOS_ID_TYP = BPRLOSS.LOS_ID_TYP;
        BPCPLOSS.DATA_INFO.LOS_ID_NO = BPRLOSS.LOS_ID_NO;
        BPCPLOSS.DATA_INFO.LOS_ORG = BPRLOSS.LOS_ORG;
        BPCPLOSS.DATA_INFO.LOS_TLR = BPRLOSS.LOS_TLR;
        BPCPLOSS.DATA_INFO.LOS_DT = BPRLOSS.LOS_DT;
        BPCPLOSS.DATA_INFO.LOS_TIME = BPRLOSS.LOS_TIME;
        BPCPLOSS.DATA_INFO.RLOS_ORG = BPRLOSS.RLOS_ORG;
        BPCPLOSS.DATA_INFO.RLOS_TLR = BPRLOSS.RLOS_TLR;
        BPCPLOSS.DATA_INFO.RLOS_DT = BPRLOSS.RLOS_DT;
        BPCPLOSS.DATA_INFO.RLOS_TIME = BPRLOSS.RLOS_TIME;
        BPCPLOSS.DATA_INFO.RLOS_DUE_TIME = BPRLOSS.RLOS_DUE_TIME;
        CEP.TRC(SCCGWA, BPCPLOSS);
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO1821_OPT_1821);
        BPCO1821_OPT_1821.LOS_NO = BPRLOSS.KEY.LOS_NO;
        BPCO1821_OPT_1821.AC = BPRLOSS.AC;
        BPCO1821_OPT_1821.AC_TYPE = BPRLOSS.AC_TYPE;
        BPCO1821_OPT_1821.STS = BPRLOSS.STS;
        BPCO1821_OPT_1821.G_AC_NO = BPRLOSS.GET_AC_NO;
        BPCO1821_OPT_1821.GET_BR = BPRLOSS.GET_BR;
        BPCO1821_OPT_1821.BV_NO = BPRLOSS.BV_NO;
        BPCO1821_OPT_1821.N_BV_NO = BPRLOSS.NEW_BV_NO;
        BPCO1821_OPT_1821.BV_TYP = BPRLOSS.BV_TYP;
        BPCO1821_OPT_1821.BV_CODE = BPRLOSS.BV_CODE;
        BPCO1821_OPT_1821.DEP_NO = BPRLOSS.DEP_NO;
        BPCO1821_OPT_1821.OPEN_BR = BPRLOSS.OPEN_BR;
        BPCO1821_OPT_1821.OPEN_AMT = BPRLOSS.OPEN_AMT;
        BPCO1821_OPT_1821.BILL_TYP = BPRLOSS.BILL_TYP;
        BPCO1821_OPT_1821.BILL_NO = BPRLOSS.BILL_NO;
        BPCO1821_OPT_1821.SUP_B_NO = BPRLOSS.SUP_BILL_NO;
        BPCO1821_OPT_1821.BILL_BR = BPRLOSS.BILL_BR;
        BPCO1821_OPT_1821.LOS_RMK = BPRLOSS.LOS_RMK;
        BPCO1821_OPT_1821.OTH_NM = BPRLOSS.OTH_NM;
        BPCO1821_OPT_1821.O_ID_TYP = BPRLOSS.OTH_ID_TYP;
        BPCO1821_OPT_1821.O_ID_NO = BPRLOSS.OTH_ID_NO;
        BPCO1821_OPT_1821.LOS_NM = BPRLOSS.LOS_NM;
        BPCO1821_OPT_1821.L_ID_TYP = BPRLOSS.LOS_ID_TYP;
        BPCO1821_OPT_1821.L_ID_NO = BPRLOSS.LOS_ID_NO;
        BPCO1821_OPT_1821.LOS_ORG = BPRLOSS.LOS_ORG;
        BPCO1821_OPT_1821.LOS_TLR = BPRLOSS.LOS_TLR;
        BPCO1821_OPT_1821.LOS_DT = BPRLOSS.LOS_DT;
        BPCO1821_OPT_1821.LOS_TIME = BPRLOSS.LOS_TIME;
        BPCO1821_OPT_1821.RLOS_ORG = BPRLOSS.RLOS_ORG;
        BPCO1821_OPT_1821.RLOS_TLR = BPRLOSS.RLOS_TLR;
        BPCO1821_OPT_1821.RLOS_DT = BPRLOSS.RLOS_DT;
        BPCO1821_OPT_1821.RL_TIME = BPRLOSS.RLOS_TIME;
        BPCO1821_OPT_1821.RL_DUE_T = BPRLOSS.RLOS_DUE_TIME;
        BPCO1821_OPT_1821.HLD_FLG = BPRLOSS.HLD_FLG;
        BPCO1821_OPT_1821.OPEN_DT = BPRLOSS.OPEN_DT;
        BPCO1821_OPT_1821.GET_NM = BPRLOSS.GET_NM;
        BPCO1821_OPT_1821.LOS_TELE = BPRLOSS.LOS_TELE;
        BPCO1821_OPT_1821.LOS_ADDR = BPRLOSS.LOS_ADDR;
        BPCO1821_OPT_1821.ID_TYP = BPRLOSS.ID_TYP;
        BPCO1821_OPT_1821.ID_NO = BPRLOSS.ID_NO;
        BPCO1821_OPT_1821.OTH_TELE = BPRLOSS.OTH_TELE;
        BPCO1821_OPT_1821.RLOS_NM = BPRLOSS.RLOS_NM;
        BPCO1821_OPT_1821.RLOS_ID_TYP = BPRLOSS.RLOS_ID_TYP;
        BPCO1821_OPT_1821.RLOS_ID_NO = BPRLOSS.RLOS_ID_NO;
        BPCO1821_OPT_1821.RLOS_TELE = BPRLOSS.RLOS_TELE;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO1820_OPT_1820);
        BPCO1820_OPT_1820.LOS_NO = BPRLOSS.KEY.LOS_NO;
        BPCO1820_OPT_1820.AC = BPRLOSS.AC;
        BPCO1820_OPT_1820.AC_TYPE = BPRLOSS.AC_TYPE;
        BPCO1820_OPT_1820.STS = BPRLOSS.STS;
        BPCO1820_OPT_1820.BV_NO = BPRLOSS.BV_NO;
        BPCO1820_OPT_1820.N_BV_NO = BPRLOSS.NEW_BV_NO;
        BPCO1820_OPT_1820.BV_TYP = BPRLOSS.BV_TYP;
        BPCO1820_OPT_1820.BV_CODE = BPRLOSS.BV_CODE;
        BPCO1820_OPT_1820.OPEN_BR = BPRLOSS.OPEN_BR;
        BPCO1820_OPT_1820.OPEN_AMT = BPRLOSS.OPEN_AMT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCO1820_OPT_1820);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO1830_OPT_1830);
        CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
        CEP.TRC(SCCGWA, BPRLOSS.AC);
        CEP.TRC(SCCGWA, BPRLOSS.AC_TYPE);
        CEP.TRC(SCCGWA, BPRLOSS.STS);
        CEP.TRC(SCCGWA, BPRLOSS.BV_NO);
        CEP.TRC(SCCGWA, BPRLOSS.NEW_BV_NO);
        CEP.TRC(SCCGWA, BPRLOSS.BV_TYP);
        CEP.TRC(SCCGWA, BPRLOSS.BV_CODE);
        CEP.TRC(SCCGWA, BPRLOSS.SUP_BILL_NO);
        CEP.TRC(SCCGWA, BPRLOSS.OPEN_BR);
        BPCO1830_OPT_1830.LOS_NO = BPRLOSS.KEY.LOS_NO;
        BPCO1830_OPT_1830.AC = BPRLOSS.AC;
        BPCO1830_OPT_1830.AC_TYPE = BPRLOSS.AC_TYPE;
        BPCO1830_OPT_1830.STS = BPRLOSS.STS;
        BPCO1830_OPT_1830.OPEN_BR = BPRLOSS.OPEN_BR;
        BPCO1830_OPT_1830.OPEN_AMT = BPRLOSS.OPEN_AMT;
        BPCO1830_OPT_1830.BILL_TYP = BPRLOSS.BILL_TYP;
        BPCO1830_OPT_1830.BILL_NO = BPRLOSS.BILL_NO;
        BPCO1830_OPT_1830.SUP_B_NO = BPRLOSS.SUP_BILL_NO;
        BPCO1830_OPT_1830.BILL_BR = BPRLOSS.BILL_BR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCO1830_OPT_1830);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = BPCO1821_OPT_1821;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTLOSS() throws IOException,SQLException,Exception {
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("1")) {
            BPRLOSS.KEY.LOS_NO = BPCPLOSS.DATA_INFO.LOS_NO;
            CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
            BPTLOSS_RD = new DBParm();
            BPTLOSS_RD.TableName = "BPTLOSS";
            BPTLOSS_RD.where = "LOS_NO = :BPRLOSS.KEY.LOS_NO";
            BPTLOSS_RD.fst = true;
            IBS.READ(SCCGWA, BPRLOSS, this, BPTLOSS_RD);
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("2")) {
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.AC);
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.STS);
            BPRLOSS.AC = BPCPLOSS.DATA_INFO.AC;
            BPRLOSS.STS = BPCPLOSS.DATA_INFO.STS;
            CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
            BPTLOSS_RD = new DBParm();
            BPTLOSS_RD.TableName = "BPTLOSS";
            BPTLOSS_RD.where = "AC = :BPRLOSS.AC "
                + "AND STS = :BPRLOSS.STS";
            BPTLOSS_RD.fst = true;
            IBS.READ(SCCGWA, BPRLOSS, this, BPTLOSS_RD);
        }
        CEP.TRC(SCCGWA, BPCPLOSS.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPLOSS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPLOSS.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOSS_NOTFND, BPCPLOSS.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLOSS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTLOSS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPCPLOSS.INFO.INDEX_FLG);
        BPRLOSS.KEY.LOS_NO = BPCPLOSS.DATA_INFO.LOS_NO;
        BPRLOSS.AC = BPCPLOSS.DATA_INFO.AC;
        BPRLOSS.BV_TYP = BPCPLOSS.DATA_INFO.BV_TYP;
        BPRLOSS.BV_NO = BPCPLOSS.DATA_INFO.BV_NO;
        BPRLOSS.ID_TYP = BPCPLOSS.DATA_INFO.ID_TYP;
        BPRLOSS.ID_NO = BPCPLOSS.DATA_INFO.ID_NO;
        BPRLOSS.LOS_ORG = BPCPLOSS.DATA_INFO.LOS_ORG;
        BPRLOSS.BILL_TYP = BPCPLOSS.DATA_INFO.BILL_TYP;
        BPRLOSS.BILL_NO = BPCPLOSS.DATA_INFO.BILL_NO;
        if (BPCPLOSS.DATA_INFO.STA_DT == ' ' 
            || BPCPLOSS.DATA_INFO.STA_DT == 0) {
            if (ALL.trim().length() == 0) WS_STA_DT = 0;
            else WS_STA_DT = Integer.parseInt(ALL);
        } else {
            WS_STA_DT = BPCPLOSS.DATA_INFO.STA_DT;
        }
        if (BPCPLOSS.DATA_INFO.END_DT == ' ' 
            || BPCPLOSS.DATA_INFO.END_DT == 0) {
            WS_END_DT = 99991231;
        } else {
            WS_END_DT = BPCPLOSS.DATA_INFO.END_DT;
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("8")) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "LOS_NO = :BPRLOSS.KEY.LOS_NO "
                + "AND ( ( LOS_DT >= :WS_STA_DT "
                + "AND LOS_DT <= :WS_END_DT ) "
                + "OR ( RLOS_DT >= :WS_STA_DT "
                + "AND RLOS_DT <= :WS_END_DT ) )";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("3")) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "AC = :BPRLOSS.AC "
                + "AND ( ( LOS_DT >= :WS_STA_DT "
                + "AND LOS_DT <= :WS_END_DT ) "
                + "OR ( RLOS_DT >= :WS_STA_DT "
                + "AND RLOS_DT <= :WS_END_DT ) )";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("4")) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "BV_TYP = :BPRLOSS.BV_TYP "
                + "AND BV_NO = :BPRLOSS.BV_NO "
                + "AND ( ( LOS_DT >= :WS_STA_DT "
                + "AND LOS_DT <= :WS_END_DT ) "
                + "OR ( RLOS_DT >= :WS_STA_DT "
                + "AND RLOS_DT <= :WS_END_DT ) )";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("5")) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "ID_TYP = :BPRLOSS.ID_TYP "
                + "AND ID_NO = :BPRLOSS.ID_NO "
                + "AND ( ( LOS_DT >= :WS_STA_DT "
                + "AND LOS_DT <= :WS_END_DT ) "
                + "OR ( RLOS_DT >= :WS_STA_DT "
                + "AND RLOS_DT <= :WS_END_DT ) )";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("6")) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "LOS_ORG = :BPRLOSS.LOS_ORG "
                + "AND ( ( LOS_DT >= :WS_STA_DT "
                + "AND LOS_DT <= :WS_END_DT ) "
                + "OR ( RLOS_DT >= :WS_STA_DT "
                + "AND RLOS_DT <= :WS_END_DT ) )";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
        if (BPCPLOSS.INFO.INDEX_FLG.equalsIgnoreCase("7")) {
            BPTLOSS_BR.rp = new DBParm();
            BPTLOSS_BR.rp.TableName = "BPTLOSS";
            BPTLOSS_BR.rp.where = "BILL_TYP = :BPRLOSS.BILL_TYP "
                + "AND BILL_NO = :BPRLOSS.BILL_NO";
            IBS.STARTBR(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        }
    }
    public void T000_READNEXT_BPTLOSS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        CEP.TRC(SCCGWA, BPCPLOSS.INFO.INDEX_FLG);
        IBS.READNEXT(SCCGWA, BPRLOSS, this, BPTLOSS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPLOSS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPLOSS.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LOSS_NOTFND, BPCPLOSS.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLOSS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTLOSS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTLOSS_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPLOSS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPOLOSS = ");
            CEP.TRC(SCCGWA, BPCPLOSS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
