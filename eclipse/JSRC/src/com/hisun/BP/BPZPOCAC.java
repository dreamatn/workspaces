package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPOCAC {
    DBParm BPTOCAC_RD;
    brParm BPTOCAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    char WS_OUT = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPROCAC BPROCAC = new BPROCAC();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCO1800_OPT_1800 BPCO1800_OPT_1800 = new BPCO1800_OPT_1800();
    BPCO1801_OPT_1801 BPCO1801_OPT_1801 = new BPCO1801_OPT_1801();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPOCAC BPCPOCAC;
    public void MP(SCCGWA SCCGWA, BPCPOCAC BPCPOCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPOCAC = BPCPOCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPOCAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPOCAC.RC);
        WS_OUT = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_TLR);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_DT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CLO_DT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.STS);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.WORK_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.AC);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.ACO_AC);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.BV_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.BV_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.ID_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.ID_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CI_CNM);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CAL_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CI_TYPE);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.LOSS_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CARD_FLG);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.SEQ);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CCY);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.AUT_TLR);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CLOSE_TLR);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.REOPN_DATE);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CLOSE_REASON);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CLOSE_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CLOSE_AC_STS);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.LOSS_RAT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.LOSS_INT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.LOSS_TAX);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.LOSS_AMT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CHNL_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_AMT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.REMARK);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.PROD_CD);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OTH_PRT_NM);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OTH_ID_NO);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_RAT);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.AC_CNM);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.COL_IND);
        if (BPCPOCAC.DATA_INFO.OPEN_DT != 0) {
            if (BPCPOCAC.DATA_INFO.OPEN_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DT_MORE_THAN_AC_DT);
            }
        }
        if (BPCPOCAC.DATA_INFO.CLO_DT != 0) {
            if (BPCPOCAC.DATA_INFO.CLO_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DT_MORE_THAN_AC_DT);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPOCAC.INFO.FUNC);
        if (BPCPOCAC.INFO.FUNC == 'I') {
            B020_INQUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCPOCAC.INFO.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCPOCAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B030_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPROCAC.KEY.AC);
        CEP.TRC(SCCGWA, BPROCAC.CAL_TYP);
        CEP.TRC(SCCGWA, BPROCAC.WORK_TYP);
        CEP.TRC(SCCGWA, BPROCAC.STS);
        CEP.TRC(SCCGWA, BPROCAC.CI_TYPE);
        CEP.TRC(SCCGWA, BPROCAC.CARD_FLG);
        CEP.TRC(SCCGWA, BPROCAC.SEQ);
        CEP.TRC(SCCGWA, BPROCAC.BV_TYP);
        CEP.TRC(SCCGWA, BPROCAC.BV_NO);
        CEP.TRC(SCCGWA, BPROCAC.CCY);
        CEP.TRC(SCCGWA, BPROCAC.CCY_TYPE);
        CEP.TRC(SCCGWA, BPROCAC.AUT_TLR);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_DATE);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_TLR);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_NO);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_DATE);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_TLR);
        CEP.TRC(SCCGWA, BPROCAC.REOPN_DATE);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_REASON);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_NO);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_AC_STS);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_RAT);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_INT);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_TAX);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_AMT);
        CEP.TRC(SCCGWA, BPROCAC.CHNL_NO);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_AMT);
        CEP.TRC(SCCGWA, BPROCAC.REMARK);
        CEP.TRC(SCCGWA, BPROCAC.PROD_CD);
        CEP.TRC(SCCGWA, BPROCAC.OTH_PRT_NM);
        CEP.TRC(SCCGWA, BPROCAC.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPROCAC.OTH_ID_NO);
        CEP.TRC(SCCGWA, BPROCAC.BR);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_TYP);
        CEP.TRC(SCCGWA, BPROCAC.COL_IND);
        BPCPOCAC.DATA_INFO.ACO_AC = BPROCAC.KEY.ACO_AC;
        BPCPOCAC.DATA_INFO.AC = BPROCAC.KEY.AC;
        BPCPOCAC.DATA_INFO.CAL_TYP = BPROCAC.CAL_TYP;
        BPCPOCAC.DATA_INFO.WORK_TYP = BPROCAC.WORK_TYP;
        BPCPOCAC.DATA_INFO.STS = BPROCAC.STS;
        BPCPOCAC.DATA_INFO.CI_TYPE = BPROCAC.CI_TYPE;
        BPCPOCAC.DATA_INFO.CARD_FLG = BPROCAC.CARD_FLG;
        BPCPOCAC.DATA_INFO.SEQ = BPROCAC.SEQ;
        BPCPOCAC.DATA_INFO.BV_TYP = BPROCAC.BV_TYP;
        BPCPOCAC.DATA_INFO.BV_NO = BPROCAC.BV_NO;
        BPCPOCAC.DATA_INFO.CCY = BPROCAC.CCY;
        BPCPOCAC.DATA_INFO.CCY_TYPE = BPROCAC.CCY_TYPE;
        BPCPOCAC.DATA_INFO.AUT_TLR = BPROCAC.AUT_TLR;
        BPCPOCAC.DATA_INFO.OPEN_DT = BPROCAC.OPEN_DATE;
        BPCPOCAC.DATA_INFO.OPEN_TLR = BPROCAC.OPEN_TLR;
        BPCPOCAC.DATA_INFO.OPEN_NO = BPROCAC.OPEN_NO;
        BPCPOCAC.DATA_INFO.CLO_DT = BPROCAC.CLOSE_DATE;
        BPCPOCAC.DATA_INFO.CLOSE_TLR = BPROCAC.CLOSE_TLR;
        BPCPOCAC.DATA_INFO.REOPN_DATE = BPROCAC.REOPN_DATE;
        BPCPOCAC.DATA_INFO.CLOSE_REASON = BPROCAC.CLOSE_REASON;
        BPCPOCAC.DATA_INFO.CLOSE_NO = BPROCAC.CLOSE_NO;
        BPCPOCAC.DATA_INFO.CLOSE_AC_STS = BPROCAC.CLOSE_AC_STS;
        BPCPOCAC.DATA_INFO.LOSS_RAT = BPROCAC.LOSS_RAT;
        BPCPOCAC.DATA_INFO.LOSS_INT = BPROCAC.LOSS_INT;
        BPCPOCAC.DATA_INFO.LOSS_TAX = BPROCAC.LOSS_TAX;
        BPCPOCAC.DATA_INFO.LOSS_AMT = BPROCAC.LOSS_AMT;
        BPCPOCAC.DATA_INFO.CHNL_NO = BPROCAC.CHNL_NO;
        BPCPOCAC.DATA_INFO.OPEN_AMT = BPROCAC.OPEN_AMT;
        BPCPOCAC.DATA_INFO.REMARK = BPROCAC.REMARK;
        BPCPOCAC.DATA_INFO.PROD_CD = BPROCAC.PROD_CD;
        BPCPOCAC.DATA_INFO.OTH_PRT_NM = BPROCAC.OTH_PRT_NM;
        BPCPOCAC.DATA_INFO.OTH_ID_TYP = BPROCAC.OTH_ID_TYP;
        BPCPOCAC.DATA_INFO.OTH_ID_NO = BPROCAC.OTH_ID_NO;
        BPCPOCAC.DATA_INFO.BR = BPROCAC.BR;
        BPCPOCAC.DATA_INFO.LOSS_TYP = BPROCAC.LOSS_TYP;
        BPCPOCAC.DATA_INFO.ID_TYP = BPROCAC.ID_TYP;
        BPCPOCAC.DATA_INFO.ID_NO = BPROCAC.ID_NO;
        BPCPOCAC.DATA_INFO.CI_CNM = BPROCAC.CI_CNM;
        BPCPOCAC.DATA_INFO.COL_IND = BPROCAC.COL_IND;
    }
    public void B020_INQUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPROCAC);
        T000_READ_BPTOCAC();
        if (pgmRtn) return;
        if (BPCPOCAC.RETURN_INFO == 'F') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B060_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPROCAC);
        T000_STARTBR_BPTOCAC();
        if (pgmRtn) return;
        T000_READNEXT_BPTOCAC();
        if (pgmRtn) return;
        if (BPCPOCAC.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCPOCAC.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (BPCPOCAC.DATA_INFO.STS != ' ') {
                if (BPCPOCAC.DATA_INFO.STS == 'N') {
                    if (BPROCAC.CLOSE_TLR.trim().length() > 0) {
                        WS_OUT = 'N';
                    } else {
                        WS_OUT = 'Y';
                    }
                } else {
                    if (BPROCAC.CLOSE_TLR.trim().length() > 0) {
                        WS_OUT = 'Y';
                    } else {
                        WS_OUT = 'N';
                    }
                }
            } else {
                WS_OUT = 'Y';
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.WORK_TYP.trim().length() > 0) {
                    if (!BPROCAC.WORK_TYP.equalsIgnoreCase(BPCPOCAC.DATA_INFO.WORK_TYP)) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.CI_CNM.trim().length() > 0) {
                    if (!BPROCAC.CI_CNM.equalsIgnoreCase(BPCPOCAC.DATA_INFO.CI_CNM)) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.OPEN_TLR.trim().length() > 0) {
                    CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_TLR);
                    CEP.TRC(SCCGWA, BPROCAC.OPEN_TLR);
                    if (BPCPOCAC.DATA_INFO.STS == 'C') {
                        if (!BPROCAC.CLOSE_TLR.equalsIgnoreCase(BPCPOCAC.DATA_INFO.OPEN_TLR)) {
                            WS_OUT = 'N';
                            CEP.TRC(SCCGWA, WS_OUT);
                        }
                    } else {
                        if (!BPROCAC.OPEN_TLR.equalsIgnoreCase(BPCPOCAC.DATA_INFO.OPEN_TLR)) {
                            WS_OUT = 'N';
                            CEP.TRC(SCCGWA, WS_OUT);
                        }
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.CCY.trim().length() > 0) {
                    if (!BPROCAC.CCY.equalsIgnoreCase(BPCPOCAC.DATA_INFO.CCY)) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.CCY_TYPE != ' ') {
                    if (BPROCAC.CCY_TYPE != BPCPOCAC.DATA_INFO.CCY_TYPE) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.SEQ != 0) {
                    CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.SEQ);
                    CEP.TRC(SCCGWA, BPROCAC.SEQ);
                    if (BPROCAC.SEQ != BPCPOCAC.DATA_INFO.SEQ) {
                        WS_OUT = 'N';
                        CEP.TRC(SCCGWA, WS_OUT);
                    }
                }
            }
            if (WS_OUT == 'Y') {
                if (BPCPOCAC.DATA_INFO.STS != ' ' 
                    && BPCPOCAC.DATA_INFO.OPEN_DT != 0 
                    && BPCPOCAC.DATA_INFO.CLO_DT != 0) {
                    CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.STS);
                    CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.OPEN_DT);
                    CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CLO_DT);
                    if (BPCPOCAC.DATA_INFO.STS == 'N') {
                        if (BPROCAC.OPEN_DATE >= BPCPOCAC.DATA_INFO.OPEN_DT 
                            && BPROCAC.OPEN_DATE <= BPCPOCAC.DATA_INFO.CLO_DT) {
                            CEP.TRC(SCCGWA, "11111");
                            WS_OUT = 'Y';
                        } else {
                            CEP.TRC(SCCGWA, "22222");
                            WS_OUT = 'N';
                        }
                    } else {
                        if (BPROCAC.CLOSE_DATE >= BPCPOCAC.DATA_INFO.OPEN_DT 
                            && BPROCAC.CLOSE_DATE <= BPCPOCAC.DATA_INFO.CLO_DT) {
                            CEP.TRC(SCCGWA, "33333");
                            WS_OUT = 'Y';
                        } else {
                            CEP.TRC(SCCGWA, "44444");
                            WS_OUT = 'N';
                        }
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_OUT);
            if (WS_OUT == 'Y') {
                B060_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTOCAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTOCAC();
        if (pgmRtn) return;
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
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO1801_OPT_1801);
        BPROCAC.KEY.ACO_AC = " ";
        CEP.TRC(SCCGWA, BPROCAC.KEY.AC);
        CEP.TRC(SCCGWA, BPROCAC.CAL_TYP);
        CEP.TRC(SCCGWA, BPROCAC.WORK_TYP);
        CEP.TRC(SCCGWA, BPROCAC.STS);
        CEP.TRC(SCCGWA, BPROCAC.CI_TYPE);
        CEP.TRC(SCCGWA, BPROCAC.CARD_FLG);
        CEP.TRC(SCCGWA, BPROCAC.SEQ);
        CEP.TRC(SCCGWA, BPROCAC.BV_TYP);
        CEP.TRC(SCCGWA, BPROCAC.BV_NO);
        CEP.TRC(SCCGWA, BPROCAC.CCY);
        CEP.TRC(SCCGWA, BPROCAC.CCY_TYPE);
        CEP.TRC(SCCGWA, BPROCAC.AUT_TLR);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_DATE);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_TLR);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_NO);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_DATE);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_TLR);
        CEP.TRC(SCCGWA, BPROCAC.REOPN_DATE);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_REASON);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_NO);
        CEP.TRC(SCCGWA, BPROCAC.CLOSE_AC_STS);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_RAT);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_INT);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_TAX);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_AMT);
        CEP.TRC(SCCGWA, BPROCAC.CHNL_NO);
        CEP.TRC(SCCGWA, BPROCAC.OPEN_AMT);
        CEP.TRC(SCCGWA, BPROCAC.REMARK);
        CEP.TRC(SCCGWA, BPROCAC.PROD_CD);
        CEP.TRC(SCCGWA, BPROCAC.OTH_PRT_NM);
        CEP.TRC(SCCGWA, BPROCAC.OTH_ID_TYP);
        CEP.TRC(SCCGWA, BPROCAC.OTH_ID_NO);
        CEP.TRC(SCCGWA, BPROCAC.BR);
        CEP.TRC(SCCGWA, BPROCAC.LOSS_TYP);
        CEP.TRC(SCCGWA, BPROCAC.COL_IND);
        BPCO1801_OPT_1801.ACO_AC = BPROCAC.KEY.ACO_AC;
        BPCO1801_OPT_1801.AC = BPROCAC.KEY.AC;
        BPCO1801_OPT_1801.CAL_TYP = BPROCAC.CAL_TYP;
        BPCO1801_OPT_1801.WORK_TYP = BPROCAC.WORK_TYP;
        BPCO1801_OPT_1801.STS = BPROCAC.STS;
        BPCO1801_OPT_1801.CI_TYPE = BPROCAC.CI_TYPE;
        BPCO1801_OPT_1801.CI_CNM = BPROCAC.CI_CNM;
        BPCO1801_OPT_1801.CARD_FLG = BPROCAC.CARD_FLG;
        BPCO1801_OPT_1801.SEQ = BPROCAC.SEQ;
        BPCO1801_OPT_1801.BV_TYP = BPROCAC.BV_TYP;
        BPCO1801_OPT_1801.BV_NO = BPROCAC.BV_NO;
        BPCO1801_OPT_1801.CCY = BPROCAC.CCY;
        BPCO1801_OPT_1801.CCY_TYPE = BPROCAC.CCY_TYPE;
        BPCO1801_OPT_1801.AUT_TLR = BPROCAC.AUT_TLR;
        BPCO1801_OPT_1801.OPEN_DT = BPROCAC.OPEN_DATE;
        BPCO1801_OPT_1801.OPEN_TLR = BPROCAC.OPEN_TLR;
        BPCO1801_OPT_1801.OPEN_NO = BPROCAC.OPEN_NO;
        BPCO1801_OPT_1801.CLO_DT = BPROCAC.CLOSE_DATE;
        BPCO1801_OPT_1801.CLO_TLR = BPROCAC.CLOSE_TLR;
        BPCO1801_OPT_1801.REOPN_DT = BPROCAC.REOPN_DATE;
        BPCO1801_OPT_1801.CLO_REA = BPROCAC.CLOSE_REASON;
        BPCO1801_OPT_1801.CLO_NO = BPROCAC.CLOSE_NO;
        BPCO1801_OPT_1801.C_AC_STS = BPROCAC.CLOSE_AC_STS;
        BPCO1801_OPT_1801.LOSS_RAT = BPROCAC.LOSS_RAT;
        BPCO1801_OPT_1801.LOSS_INT = BPROCAC.LOSS_INT;
        BPCO1801_OPT_1801.LOSS_TAX = BPROCAC.LOSS_TAX;
        BPCO1801_OPT_1801.LOSS_AMT = BPROCAC.LOSS_AMT;
        BPCO1801_OPT_1801.CHNL_NO = BPROCAC.CHNL_NO;
        BPCO1801_OPT_1801.OPEN_AMT = BPROCAC.OPEN_AMT;
        BPCO1801_OPT_1801.REMARK = BPROCAC.REMARK;
        BPCO1801_OPT_1801.PROD_CD = BPROCAC.PROD_CD;
        BPCO1801_OPT_1801.O_RPT_NM = BPROCAC.OTH_PRT_NM;
        BPCO1801_OPT_1801.O_ID_TYP = BPROCAC.OTH_ID_TYP;
        BPCO1801_OPT_1801.O_ID_NO = BPROCAC.OTH_ID_NO;
        BPCO1801_OPT_1801.BR = BPROCAC.BR;
        BPCO1801_OPT_1801.LOSS_TYP = BPROCAC.LOSS_TYP;
        BPCO1801_OPT_1801.OPEN_RAT = BPROCAC.OPEN_RAT;
        BPCO1801_OPT_1801.AC_CNM = BPROCAC.AC_CNM;
        BPCO1801_OPT_1801.COL_IND = BPROCAC.COL_IND;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO1800_OPT_1800);
        BPCO1800_OPT_1800.ACO_AC = BPROCAC.KEY.ACO_AC;
        BPCO1800_OPT_1800.AC = BPROCAC.KEY.AC;
        BPCO1800_OPT_1800.CAL_TYP = BPROCAC.CAL_TYP;
        BPCO1800_OPT_1800.WORK_TYP = BPROCAC.WORK_TYP;
        BPCO1800_OPT_1800.STS = BPROCAC.STS;
        BPCO1800_OPT_1800.CCY = BPROCAC.CCY;
        BPCO1800_OPT_1800.CCY_TYPE = BPROCAC.CCY_TYPE;
        BPCO1800_OPT_1800.CI_TYPE = BPROCAC.CI_TYPE;
        BPCO1800_OPT_1800.CI_CNM = BPROCAC.CI_CNM;
        BPCO1800_OPT_1800.CARD_FLG = BPROCAC.CARD_FLG;
        BPCO1800_OPT_1800.SEQ = BPROCAC.SEQ;
        BPCO1800_OPT_1800.BV_TYP = BPROCAC.BV_TYP;
        BPCO1800_OPT_1800.BV_NO = BPROCAC.BV_NO;
        BPCO1800_OPT_1800.COL_IND = BPROCAC.COL_IND;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCO1800_OPT_1800);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCPOCAC.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO1801_OPT_1801;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTOCAC() throws IOException,SQLException,Exception {
        BPROCAC.KEY.AC = BPCPOCAC.DATA_INFO.AC;
        BPROCAC.KEY.ACO_AC = BPCPOCAC.DATA_INFO.ACO_AC;
        BPTOCAC_RD = new DBParm();
        BPTOCAC_RD.TableName = "BPTOCAC";
        BPTOCAC_RD.where = "AC = :BPROCAC.KEY.AC "
            + "AND ACO_AC = :BPROCAC.KEY.ACO_AC";
        BPTOCAC_RD.fst = true;
        IBS.READ(SCCGWA, BPROCAC, this, BPTOCAC_RD);
        CEP.TRC(SCCGWA, BPCPOCAC.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPOCAC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPOCAC.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND, BPCPOCAC.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTOCAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPCPOCAC.INFO.INDEX_FLG);
        BPROCAC.BV_TYP = BPCPOCAC.DATA_INFO.BV_TYP;
        BPROCAC.BV_NO = BPCPOCAC.DATA_INFO.BV_NO;
        BPROCAC.ID_TYP = BPCPOCAC.DATA_INFO.ID_TYP;
        BPROCAC.ID_NO = BPCPOCAC.DATA_INFO.ID_NO;
        BPROCAC.CI_CNM = BPCPOCAC.DATA_INFO.CI_CNM;
        BPROCAC.BR = BPCPOCAC.DATA_INFO.BR;
        BPROCAC.OPEN_DATE = BPCPOCAC.DATA_INFO.OPEN_DT;
        BPROCAC.KEY.AC = BPCPOCAC.DATA_INFO.AC;
        BPROCAC.WORK_TYP = BPCPOCAC.DATA_INFO.WORK_TYP;
        BPROCAC.COL_IND = BPCPOCAC.DATA_INFO.COL_IND;
        if (BPCPOCAC.DATA_INFO.OPEN_DT == ' ' 
            || BPCPOCAC.DATA_INFO.OPEN_DT == 0) {
            if (ALL.trim().length() == 0) BPROCAC.OPEN_DATE = 0;
            else BPROCAC.OPEN_DATE = Integer.parseInt(ALL);
        } else {
            BPROCAC.OPEN_DATE = BPCPOCAC.DATA_INFO.OPEN_DT;
        }
        if (BPCPOCAC.DATA_INFO.CLO_DT == ' ' 
            || BPCPOCAC.DATA_INFO.CLO_DT == 0) {
            BPROCAC.CLOSE_DATE = 99991231;
        } else {
            BPROCAC.CLOSE_DATE = BPCPOCAC.DATA_INFO.CLO_DT;
        }
        if (BPCPOCAC.INFO.INDEX_FLG.equalsIgnoreCase("4")) {
            BPTOCAC_BR.rp = new DBParm();
            BPTOCAC_BR.rp.TableName = "BPTOCAC";
            BPTOCAC_BR.rp.where = "BV_TYP = :BPROCAC.BV_TYP "
                + "AND BV_NO = :BPROCAC.BV_NO "
                + "AND ( ( OPEN_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) "
                + "OR ( CLOSE_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) )";
            BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        }
        if (BPCPOCAC.INFO.INDEX_FLG.equalsIgnoreCase("5")) {
            CEP.TRC(SCCGWA, BPROCAC.ID_TYP);
            CEP.TRC(SCCGWA, BPROCAC.ID_NO);
            BPTOCAC_BR.rp = new DBParm();
            BPTOCAC_BR.rp.TableName = "BPTOCAC";
            BPTOCAC_BR.rp.where = "ID_TYP = :BPROCAC.ID_TYP "
                + "AND ID_NO = :BPROCAC.ID_NO "
                + "AND ( ( OPEN_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) "
                + "OR ( CLOSE_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) )";
            BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        }
        if (BPCPOCAC.INFO.INDEX_FLG.equalsIgnoreCase("2")) {
            if (BPCPOCAC.DATA_INFO.STS == 'C') {
                BPTOCAC_BR.rp = new DBParm();
                BPTOCAC_BR.rp.TableName = "BPTOCAC";
                BPTOCAC_BR.rp.where = "CLO_BR = :BPROCAC.BR "
                    + "AND ( ( OPEN_DATE BETWEEN :BPROCAC.OPEN_DATE "
                    + "AND :BPROCAC.CLOSE_DATE ) "
                    + "OR ( CLOSE_DATE BETWEEN :BPROCAC.OPEN_DATE "
                    + "AND :BPROCAC.CLOSE_DATE ) )";
                BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
                IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
            } else {
                BPTOCAC_BR.rp = new DBParm();
                BPTOCAC_BR.rp.TableName = "BPTOCAC";
                BPTOCAC_BR.rp.where = "BR = :BPROCAC.BR "
                    + "AND ( ( OPEN_DATE BETWEEN :BPROCAC.OPEN_DATE "
                    + "AND :BPROCAC.CLOSE_DATE ) "
                    + "OR ( CLOSE_DATE BETWEEN :BPROCAC.OPEN_DATE "
                    + "AND :BPROCAC.CLOSE_DATE ) )";
                BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
                IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
            }
        }
        if (BPCPOCAC.INFO.INDEX_FLG.equalsIgnoreCase("1")) {
            BPTOCAC_BR.rp = new DBParm();
            BPTOCAC_BR.rp.TableName = "BPTOCAC";
            BPTOCAC_BR.rp.where = "AC = :BPROCAC.KEY.AC";
            BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        }
        if (BPCPOCAC.INFO.INDEX_FLG.equalsIgnoreCase("7")) {
            BPTOCAC_BR.rp = new DBParm();
            BPTOCAC_BR.rp.TableName = "BPTOCAC";
            BPTOCAC_BR.rp.where = "ID_TYP = :BPROCAC.ID_TYP "
                + "AND ID_NO = :BPROCAC.ID_NO "
                + "AND COL_IND = :BPROCAC.COL_IND "
                + "AND ( ( OPEN_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) "
                + "OR ( CLOSE_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) )";
            BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        }
        if (BPCPOCAC.INFO.INDEX_FLG.equalsIgnoreCase("8")) {
            BPTOCAC_BR.rp = new DBParm();
            BPTOCAC_BR.rp.TableName = "BPTOCAC";
            BPTOCAC_BR.rp.where = "CI_CNM LIKE :BPROCAC.CI_CNM "
                + "AND COL_IND = :BPROCAC.COL_IND "
                + "AND ( ( OPEN_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) "
                + "OR ( CLOSE_DATE BETWEEN :BPROCAC.OPEN_DATE "
                + "AND :BPROCAC.CLOSE_DATE ) )";
            BPTOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        }
    }
    public void T000_READNEXT_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        CEP.TRC(SCCGWA, BPCPOCAC.INFO.INDEX_FLG);
        BPTOCAC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPROCAC, this, BPTOCAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPOCAC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPOCAC.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND, BPCPOCAC.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTOCAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTOCAC_BR);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPOCAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPOACAC = ");
            CEP.TRC(SCCGWA, BPCPOCAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
