package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZITRSR {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIACCY_RD;
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DC";
    String K_CUS_CR_STS_TBL = "0001";
    String K_CUS_DR_STS_TBL = "0002";
    String WS_VIA_AC = " ";
    double WS_IACCY_AVL_BAL = 0;
    char WS_IACCY_REC_FLG = ' ';
    char WS_IAACR_REC_FLG = ' ';
    char WS_SPAC_REC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCITRSR DCCITRSR;
    public void MP(SCCGWA SCCGWA, DCCITRSR DCCITRSR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCITRSR = DCCITRSR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZITRSR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCITRSR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B010_CHECK_CCY_PROC();
        if (pgmRtn) return;
        if (DCCITRSR.INP_DATA.OPR == 'L') {
            B020_LNK_OPR_PROC();
            if (pgmRtn) return;
        } else {
            B020_TRS_OPR_PROC();
            if (pgmRtn) return;
        }
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.AC);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.OPR);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.DRCR_FLG);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.TRS_AMT);
        if (DCCITRSR.INP_DATA.OPR != 'C' 
            && DCCITRSR.INP_DATA.OPR != 'T' 
            && DCCITRSR.INP_DATA.OPR != 'L') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_OPR_INVALID, DCCITRSR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCITRSR.INP_DATA.OPR == 'L') {
            if (DCCITRSR.INP_DATA.VIA_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (DCCITRSR.INP_DATA.AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SUB_AC_MUST_INPUT, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (DCCITRSR.INP_DATA.OPR == 'T') {
            if (DCCITRSR.INP_DATA.CCY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_M_INPUT, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (DCCITRSR.INP_DATA.CCY_TYPE != '1' 
                && DCCITRSR.INP_DATA.CCY_TYPE != '2') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (DCCITRSR.INP_DATA.CCY.equalsIgnoreCase("156") 
                && DCCITRSR.INP_DATA.CCY_TYPE == '2') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (DCCITRSR.INP_DATA.DRCR_FLG != 'C' 
                && DCCITRSR.INP_DATA.DRCR_FLG != 'D') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DRCR_INVALID, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (!DCCITRSR.INP_DATA.APP.equalsIgnoreCase("DD") 
                && !DCCITRSR.INP_DATA.APP.equalsIgnoreCase("TD")) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_APP_INVALID, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DCCITRSR.INP_DATA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCITRSR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_LNK_OPR_PROC() throws IOException,SQLException,Exception {
        WS_VIA_AC = DCCITRSR.INP_DATA.VIA_AC;
        R000_CHK_TRS_PROC();
        if (pgmRtn) return;
        R000_CHK_STS_TBL_PROC();
        if (pgmRtn) return;
        R000_TRS_OPR_PROC();
        if (pgmRtn) return;
    }
    public void B020_TRS_OPR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.SUB_AC = DCCITRSR.INP_DATA.AC;
        T00_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T00_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        while (WS_IAACR_REC_FLG != 'N') {
            if (DCRIAACR.ACCR_FLG == '1') {
                WS_VIA_AC = " ";
                WS_VIA_AC = DCRIAACR.KEY.VIA_AC;
                R000_CHK_TRS_PROC();
                if (pgmRtn) return;
                R000_CHK_STS_TBL_PROC();
                if (pgmRtn) return;
                if (DCCITRSR.INP_DATA.OPR == 'T') {
                    R000_TRS_OPR_PROC();
                    if (pgmRtn) return;
                }
            }
            T00_READNEXT_DCTIAACR();
            if (pgmRtn) return;
        }
        T00_ENDBR_DCTIAACR();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
    }
    public void R000_CHK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        if (DCCITRSR.INP_DATA.LAW_FLG != '1') {
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
            } else {
                BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
                } else {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
                }
            }
            CEP.TRC(SCCGWA, DCCIMSTR.DATA.STS_WORD);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
            JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DCCIMSTR.DATA.STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + DCCIMSTR.DATA.STS_WORD.length() - 1);
            if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
            JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if ((JIBS_tmp_str[0].equalsIgnoreCase("0111804") 
                || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) 
                && DCCIMSTR.DATA.STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 107 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(107 + 1 - 1);
            }
            if (DCCITRSR.INP_DATA.DOR_FLG == '1') {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 102 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(102 + 1 - 1);
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 105 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(105 + 1 - 1);
            }
            S000_CALL_BPZFCSTS();
            if (pgmRtn) return;
        }
        if (DCCITRSR.INP_DATA.LAW_FLG == '1') {
            if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
            JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
            if (DCCIMSTR.DATA.STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MOR_L, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHK_TRS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = WS_VIA_AC;
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
        if (DCCIMSTR.DATA.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_MST_CLOSE, DCCITRSR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_TRS_OPR_PROC() throws IOException,SQLException,Exception {
        R000_GET_CCY_PROC();
        if (pgmRtn) return;
        if (DCCITRSR.INP_DATA.DRCR_FLG == 'D' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_IACCY_AVL_BAL < DCCITRSR.INP_DATA.TRS_AMT) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AVL_BAL_NOT_ENOUGH, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (DCCITRSR.INP_DATA.DRCR_FLG == 'C' 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (WS_IACCY_AVL_BAL < DCCITRSR.INP_DATA.TRS_AMT) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AVL_BAL_NOT_ENOUGH, DCCITRSR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_IACCY_REC_FLG == 'Y') {
            R000_UPD_CCY_PROC();
            if (pgmRtn) return;
        } else {
            R000_CRT_CCY_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = WS_VIA_AC;
        DCRIACCY.KEY.CCY = DCCITRSR.INP_DATA.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCITRSR.INP_DATA.CCY_TYPE;
        T000_READUPD_DCTIACCY();
        if (pgmRtn) return;
        if (DCCITRSR.INP_DATA.LAW_FLG == '1' 
            || DCCITRSR.INP_DATA.TTOD_FLG == '1') {
            WS_IACCY_AVL_BAL = DCRIACCY.DD_TOT_BAL + DCRIACCY.TD_TOT_BAL;
        } else {
            if (DCRIACCY.HLD_MTH == '1') {
                WS_IACCY_AVL_BAL = DCRIACCY.DD_TOT_BAL + DCRIACCY.TD_TOT_BAL - DCRIACCY.TOT_HLD_BAL;
            } else {
                WS_IACCY_AVL_BAL = DCRIACCY.DD_TOT_BAL + DCRIACCY.TD_TOT_BAL;
            }
        }
        CEP.TRC(SCCGWA, WS_IACCY_AVL_BAL);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.TRS_AMT);
    }
    public void R000_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        if (DCCITRSR.INP_DATA.APP.equalsIgnoreCase("DD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.DD_ACDT) {
                DCRIACCY.LAST_DD_BAL = DCRIACCY.DD_TOT_BAL;
                DCRIACCY.LAST_DD_ACDT = DCRIACCY.DD_ACDT;
                DCRIACCY.DD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            } else {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            }
        }
        if (DCCITRSR.INP_DATA.APP.equalsIgnoreCase("TD")) {
            if (SCCGWA.COMM_AREA.AC_DATE != DCRIACCY.TD_ACDT) {
                DCRIACCY.LAST_TD_BAL = DCRIACCY.TD_TOT_BAL;
                DCRIACCY.LAST_TD_ACDT = DCRIACCY.TD_ACDT;
                DCRIACCY.TD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            } else {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            }
        }
        DCRIACCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void R000_CRT_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = WS_VIA_AC;
        DCRIACCY.KEY.CCY = DCCITRSR.INP_DATA.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCITRSR.INP_DATA.CCY_TYPE;
        if (DCCITRSR.INP_DATA.APP.equalsIgnoreCase("DD")) {
            DCRIACCY.DD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            } else {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            }
        }
        if (DCCITRSR.INP_DATA.APP.equalsIgnoreCase("TD")) {
            DCRIACCY.TD_ACDT = SCCGWA.COMM_AREA.AC_DATE;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            } else {
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'C') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL + DCCITRSR.INP_DATA.TRS_AMT;
                }
                if (DCCITRSR.INP_DATA.DRCR_FLG == 'D') {
                    DCRIACCY.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL - DCCITRSR.INP_DATA.TRS_AMT;
                }
            }
        }
        DCRIACCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void R000_GET_MST_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = DCRIAACR.KEY.VIA_AC;
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCITRSR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "SUB_AC = :DCRIAACR.SUB_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IAACR_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARTBR DCTIAACR ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IAACR_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READNEXT DCTIAACR ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ENDBR    DCTIAACR ERROR*";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        DCTIACCY_RD.upd = true;
        DCTIACCY_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IACCY_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IACCY_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIACCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        DCTIACCY_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIACCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        DCTIACCY_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CCY_RCD_ALR_EXS, DCCITRSR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCITRSR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
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
        if (DCCITRSR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCITRSR=");
            CEP.TRC(SCCGWA, DCCITRSR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
