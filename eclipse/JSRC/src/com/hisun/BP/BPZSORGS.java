package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSORGS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String RTBK_TYPE = "CPN";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG";
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    int WS_CNT_INIT = 3000;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_BNO_NUM = 0;
    int WS_PAGE_STRNO = 0;
    int WS_PAGE_ENDNO = 0;
    int WS_CNT_PG = 0;
    int WS_PAGE_ROW = 0;
    int WS_CNT = 0;
    int WS_REC_CNT = 0;
    char WS_FINIST = ' ';
    int WS_NEW_ORG = 0;
    BPZSORGS_WS_ORGM_KEY WS_ORGM_KEY = new BPZSORGS_WS_ORGM_KEY();
    int WS_COUNT_NO = 0;
    String WS_PARM_BNK = " ";
    char WS_OUT_FLG = ' ';
    int WS_COMMING_CNT = 0;
    BPZSORGS_WS_ORGM_OLD_KEY WS_ORGM_OLD_KEY = new BPZSORGS_WS_ORGM_OLD_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    SCCGWA SCCGWA;
    BPCSORGS BPCSORGS;
    public void MP(SCCGWA SCCGWA, BPCSORGS BPCSORGS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSORGS = BPCSORGS;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSORGS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSORGS.RC);
        WS_OUT_FLG = 'N';
        WS_FINIST = 'Y';
        SCCGWA.COMM_AREA.TR_BANK = "043";
        WS_PARM_BNK = SCCGWA.COMM_AREA.TR_BANK;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        B200_WRITE_PAGE();
        if (pgmRtn) return;
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSORGS.INP.BNO);
        CEP.TRC(SCCGWA, BPCSORGS.INP.PG_NUM);
        CEP.TRC(SCCGWA, BPCSORGS.INP.PG_ROW);
        CEP.TRC(SCCGWA, BPCSORGS.INP.STS);
        CEP.TRC(SCCGWA, BPCSORGS.INP.SEL_FLG);
        CEP.TRC(SCCGWA, BPCSORGS.INP.BNO_CHK);
        if (BPCSORGS.INP.PG_NUM < 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PAGE_NO_MUST, BPCSORGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSORGS.INP.PG_ROW == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PAGE_RECNO_NONE, BPCSORGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_WRITE_PAGE() throws IOException,SQLException,Exception {
        B201_PREPARE_DATA();
        if (pgmRtn) return;
        if (BPCSORGS.INP.PG_NUM > 1 
            && BPCSORGS.INP.CNT == 'N') {
            CEP.TRC(SCCGWA, "FROM 2 TO LAST PAGE-1");
            B400_CYCL_BROWSE_CN();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FROM 1 OR LAST PAGE");
            B300_COUNTPG_CN();
            if (pgmRtn) return;
        }
    }
    public void B201_PREPARE_DATA() throws IOException,SQLException,Exception {
        if (BPCSORGS.INP.PG_NUM == 0) {
            CEP.TRC(SCCGWA, "FIRST_CHECK_RECORD");
            WS_PAGE_ENDNO = BPCSORGS.INP.PG_ROW;
            WS_PAGE_STRNO = 0;
        } else {
            CEP.TRC(SCCGWA, "NOT_FIRST_CHECK_RECORD");
            WS_PAGE_ENDNO = BPCSORGS.INP.PG_NUM * BPCSORGS.INP.PG_ROW;
            WS_PAGE_STRNO = WS_PAGE_ENDNO - BPCSORGS.INP.PG_ROW;
        }
    }
    public void B300_COUNTPG_CN() throws IOException,SQLException,Exception {
        if (BPCSORGS.INP.PG_NUM <= 1 
            || BPCSORGS.INP.CNT == 'Y') {
            IBS.init(SCCGWA, BPRPORGM);
            IBS.init(SCCGWA, BPCRORGM);
            BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            WS_ORGM_KEY.WS_ORGM_BNK = WS_PARM_BNK;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
            T000_STARTBR_CN();
            if (pgmRtn) return;
            T101_READNEXTBR_CN();
            if (pgmRtn) return;
            B500_POINT_BPRPORGM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
            WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
            CEP.TRC(SCCGWA, BPRPORGM.SUPR_BR);
            CEP.TRC(SCCGWA, BPRPORGM.BRANCH_BR);
            CEP.TRC(SCCGWA, BPCSORGS.INP.BNO);
            CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
            CEP.TRC(SCCGWA, BPCSORGS.INP.SEL_FLG);
            if (BPCSORGS.INP.BNO_CHK == 'Y') {
                if (BPCSORGS.INP.SEL_FLG == '1') {
                    if (WS_FINIST == 'Y') {
                        while (WS_FINIST != 'N' 
                            && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(WS_PARM_BNK) 
                            && WS_REC_CNT < BPCSORGS.INP.PG_ROW 
                            && WS_CNT <= WS_CNT_INIT 
                            && WS_OUT_FLG != 'Y') {
                            IBS.init(SCCGWA, BPCPQORG);
                            CEP.TRC(SCCGWA, BPRPORGM.LVL);
                            CEP.TRC(SCCGWA, BPRPORGM.LVL);
                            CEP.TRC(SCCGWA, WS_ORGM_KEY.WS_ORGM_BR);
                            if (WS_ORGM_KEY.WS_ORGM_BR == BPCSORGS.INP.BNO) {
                                CEP.TRC(SCCGWA, "SINGLE SMALL BANK");
                                B600_COUNT_CALL_BPZPQORG();
                                if (pgmRtn) return;
                                WS_OUT_FLG = 'Y';
                            }
                            WS_CNT += 1;
                            T101_READNEXTBR_VARYING_CN();
                            if (pgmRtn) return;
                            B500_POINT_BPRPORGM();
                            if (pgmRtn) return;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
                            WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                        }
                    }
                } else {
                    while (WS_FINIST != 'N' 
                        && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(WS_PARM_BNK) 
                        && WS_CNT <= WS_CNT_INIT) {
                        IBS.init(SCCGWA, BPCPQORG);
                        if ((BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                            && BPRPORGM.BRANCH_BR == BPCSORGS.INP.BNO 
                            && BPRPORGM.ATTR != '0') 
                            || (BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                            && BPRPORGM.SUPR_BR == BPCSORGS.INP.BNO) 
                            || WS_ORGM_KEY.WS_ORGM_BR == BPCSORGS.INP.BNO) {
                            B600_COUNT_CALL_BPZPQORG();
                            if (pgmRtn) return;
                        }
                        WS_CNT += 1;
                        T101_READNEXTBR_VARYING_CN();
                        if (pgmRtn) return;
                        B500_POINT_BPRPORGM();
                        if (pgmRtn) return;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
                        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    }
                    T200_ENDBR_CN();
                    if (pgmRtn) return;
                }
            } else {
                if (BPCSORGS.INP.SEL_FLG == '3') {
                    while (WS_FINIST != 'N' 
                        && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(WS_PARM_BNK) 
                        && WS_CNT <= WS_CNT_INIT) {
                        IBS.init(SCCGWA, BPCPQORG);
                        if (BPRPORGM.ATTR != '0') {
                            B600_COUNT_CALL_BPZPQORG();
                            if (pgmRtn) return;
                        }
                        WS_CNT += 1;
                        T101_READNEXTBR_VARYING_CN();
                        if (pgmRtn) return;
                        B500_POINT_BPRPORGM();
                        if (pgmRtn) return;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
                        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    }
                    T200_ENDBR_CN();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            BPCSORGS.OUT.NUM = WS_COUNT_NO;
            R010_CALC_ROW_CN();
            if (pgmRtn) return;
        }
        BPCSORGS.OUT.PAGE = (short) BPCSORGS.INP.PG_NUM;
        if (BPCSORGS.OUT.PAGE == 0) {
            BPCSORGS.OUT.PAGE = 1;
        }
        if (BPCSORGS.INP.CNT == 'Y' 
            || BPCSORGS.OUT.PAGE == 1) {
            BPCSORGS.OUT.PAGE = 'Y';
        } else {
            BPCSORGS.OUT.PAGE = 'N';
        }
    }
    public void B400_CYCL_BROWSE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        WS_CNT = 0;
        IBS.init(SCCGWA, WS_ORGM_KEY);
        WS_REC_CNT = 0;
        WS_OUT_FLG = ' ';
        IBS.init(SCCGWA, WS_ORGM_OLD_KEY);
        IBS.init(SCCGWA, BPCRORGM);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = WS_PARM_BNK;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        T000_STARTBR_CN();
        if (pgmRtn) return;
        T101_READNEXTBR_CN();
        if (pgmRtn) return;
        B500_POINT_BPRPORGM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        if (BPCSORGS.INP.BNO_CHK == 'Y') {
            if (BPCSORGS.INP.SEL_FLG == '1') {
                CEP.TRC(SCCGWA, "NET BAK");
                if (WS_FINIST == 'Y') {
                    while (WS_FINIST != 'N' 
                        && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(WS_PARM_BNK) 
                        && WS_REC_CNT < BPCSORGS.INP.PG_ROW 
                        && WS_CNT <= WS_CNT_INIT 
                        && WS_OUT_FLG != 'Y') {
                        IBS.init(SCCGWA, BPCPQORG);
                        CEP.TRC(SCCGWA, BPRPORGM.LVL);
                        CEP.TRC(SCCGWA, BPRPORGM.LVL);
                        CEP.TRC(SCCGWA, WS_ORGM_KEY.WS_ORGM_BNK);
                        CEP.TRC(SCCGWA, WS_ORGM_KEY.WS_ORGM_BR);
                        if (WS_ORGM_KEY.WS_ORGM_BR == BPCSORGS.INP.BNO) {
                            CEP.TRC(SCCGWA, "SINGLE SMALL BANK");
                            B600_PAGE_ZERO_CALL();
                            if (pgmRtn) return;
                            WS_OUT_FLG = 'Y';
                        }
                        WS_CNT += 1;
                        T101_READNEXTBR_VARYING_CN();
                        if (pgmRtn) return;
                        B500_POINT_BPRPORGM();
                        if (pgmRtn) return;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
                        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    }
                }
            }
            if (BPCSORGS.INP.SEL_FLG == '2') {
                CEP.TRC(SCCGWA, "--ORG BELOW--");
                CEP.TRC(SCCGWA, WS_REC_CNT);
                CEP.TRC(SCCGWA, BPRPORGM.SUPR_BR);
                CEP.TRC(SCCGWA, BPRPORGM.BRANCH_BR);
                CEP.TRC(SCCGWA, BPCSORGS.INP.BNO);
                CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
                while (WS_FINIST != 'N' 
                    && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(WS_PARM_BNK) 
                    && WS_REC_CNT < BPCSORGS.INP.PG_ROW 
                    && WS_CNT <= WS_CNT_INIT 
                    && WS_OUT_FLG != 'Y') {
                    IBS.init(SCCGWA, BPCPQORG);
                    if ((BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                        && BPRPORGM.BRANCH_BR == BPCSORGS.INP.BNO 
                        && BPRPORGM.ATTR != '0') 
                        || (BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                        && BPRPORGM.SUPR_BR == BPCSORGS.INP.BNO) 
                        || (WS_ORGM_KEY.WS_ORGM_BR == BPCSORGS.INP.BNO)) {
                        CEP.TRC(SCCGWA, WS_REC_CNT);
                        CEP.TRC(SCCGWA, WS_PAGE_STRNO);
                        CEP.TRC(SCCGWA, WS_PAGE_ENDNO);
                        if ((WS_PAGE_STRNO == 0 
                            && WS_PAGE_ENDNO == BPCSORGS.INP.PG_ROW) 
                            || (WS_PAGE_STRNO == 1 
                            && WS_PAGE_ENDNO == BPCSORGS.INP.PG_ROW)) {
                            B600_PAGE_ZERO_CALL();
                            if (pgmRtn) return;
                        } else {
                            B700_PAGE_DOWN_CALL();
                            if (pgmRtn) return;
                        }
                    }
                    WS_CNT += 1;
                    T101_READNEXTBR_VARYING_CN();
                    if (pgmRtn) return;
                    B500_POINT_BPRPORGM();
                    if (pgmRtn) return;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
                    WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                }
                T200_ENDBR_CN();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSORGS.INP.SEL_FLG == '3') {
                CEP.TRC(SCCGWA, "--ORG ALL--");
                while (WS_FINIST != 'N' 
                    && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(WS_PARM_BNK) 
                    && WS_REC_CNT < BPCSORGS.INP.PG_ROW 
                    && WS_CNT <= WS_CNT_INIT 
                    && WS_OUT_FLG != 'Y') {
                    IBS.init(SCCGWA, BPCPQORG);
                    if (BPRPORGM.ATTR != '0') {
                        CEP.TRC(SCCGWA, WS_REC_CNT);
                        CEP.TRC(SCCGWA, WS_PAGE_STRNO);
                        CEP.TRC(SCCGWA, WS_PAGE_ENDNO);
                        CEP.TRC(SCCGWA, WS_COMMING_CNT);
                        if ((WS_PAGE_STRNO == 0 
                            && WS_PAGE_ENDNO == BPCSORGS.INP.PG_ROW) 
                            || (WS_PAGE_STRNO == 1 
                            && WS_PAGE_ENDNO == BPCSORGS.INP.PG_ROW)) {
                            B600_PAGE_ZERO_CALL();
                            if (pgmRtn) return;
                        } else {
                            B700_PAGE_DOWN_CALL();
                            if (pgmRtn) return;
                        }
                    }
                    WS_CNT += 1;
                    T101_READNEXTBR_VARYING_CN();
                    if (pgmRtn) return;
                    B500_POINT_BPRPORGM();
                    if (pgmRtn) return;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ORGM_OLD_KEY);
                    WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                }
            }
        }
        BPCSORGS.OUT.PAGE = (short) BPCSORGS.INP.PG_NUM;
        if (BPCSORGS.INP.CNT == 'Y' 
            || BPCSORGS.OUT.PAGE == 0) {
            BPCSORGS.OUT.PAGE = 'Y';
        } else {
            BPCSORGS.OUT.PAGE = 'N';
        }
        CEP.TRC(SCCGWA, BPCSORGS.OUT.PAGE);
        CEP.TRC(SCCGWA, BPCSORGS.OUT.NUM);
        CEP.TRC(SCCGWA, BPCSORGS.OUT.PAGE);
        CEP.TRC(SCCGWA, BPCSORGS.OUT.PAGE);
        CEP.TRC(SCCGWA, BPCSORGS.OUT.PAGE_ROW);
    }
    public void B500_POINT_BPRPORGM() throws IOException,SQLException,Exception {
        if (WS_FINIST == 'N') {
            CEP.TRC(SCCGWA, "ERROR");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCSORGS.RC);
        } else {
            CEP.TRC(SCCGWA, "WS-END3");
        }
    }
    public void B600_COUNT_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        BPCPQORG.BNK = WS_PARM_BNK;
        BPCPQORG.BR = WS_ORGM_KEY.WS_ORGM_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PAGE_STRNO);
        CEP.TRC(SCCGWA, WS_PAGE_ENDNO);
        if (BPCSORGS.INP.STS == BPCPQORG.ORG_STS) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
            if (!JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                WS_COUNT_NO += 1;
                CEP.TRC(SCCGWA, WS_COUNT_NO);
                if ((WS_COUNT_NO > WS_PAGE_STRNO 
                    && WS_COUNT_NO <= WS_PAGE_ENDNO)) {
                    WS_REC_CNT += 1;
                    CEP.TRC(SCCGWA, WS_REC_CNT);
                    BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                    R020_RTN_CHANGE_STS();
                    if (pgmRtn) return;
                    BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                }
                CEP.TRC(SCCGWA, "SING");
            }
        } else {
            if (BPCSORGS.INP.STS == 'CF' 
                || BPCSORGS.INP.STS == ' ') {
                if (BPCSORGS.INP.STS == ' ') {
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
                    if (!JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                        WS_COUNT_NO += 1;
                        CEP.TRC(SCCGWA, WS_COUNT_NO);
                        if ((WS_COUNT_NO > WS_PAGE_STRNO 
                            && WS_COUNT_NO <= WS_PAGE_ENDNO)) {
                            WS_REC_CNT += 1;
                            CEP.TRC(SCCGWA, WS_REC_CNT);
                            BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                            R020_RTN_CHANGE_STS();
                            if (pgmRtn) return;
                            BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                        }
                    }
                } else {
                    if (BPCPQORG.ORG_STS == 'C' 
                        || BPCPQORG.ORG_STS == 'F') {
                        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
                        if (!JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                            WS_COUNT_NO += 1;
                            CEP.TRC(SCCGWA, WS_COUNT_NO);
                            if ((WS_COUNT_NO > WS_PAGE_STRNO 
                                && WS_COUNT_NO <= WS_PAGE_ENDNO)) {
                                WS_REC_CNT += 1;
                                CEP.TRC(SCCGWA, WS_REC_CNT);
                                BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                                R020_RTN_CHANGE_STS();
                                if (pgmRtn) return;
                                BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                            }
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_COUNT_NO);
    }
    public void B600_PAGE_ZERO_CALL() throws IOException,SQLException,Exception {
        BPCPQORG.BNK = WS_PARM_BNK;
        BPCPQORG.BR = WS_ORGM_KEY.WS_ORGM_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCSORGS.INP.STS == BPCPQORG.ORG_STS) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
            if (!JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                WS_REC_CNT += 1;
                BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                R020_RTN_CHANGE_STS();
                if (pgmRtn) return;
                BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                CEP.TRC(SCCGWA, "SING");
            }
        } else {
            if (BPCSORGS.INP.STS == 'CF' 
                || BPCSORGS.INP.STS == ' ') {
                if (BPCSORGS.INP.STS == ' ') {
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
                    if (!JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                        WS_REC_CNT += 1;
                        BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                        R020_RTN_CHANGE_STS();
                        if (pgmRtn) return;
                        BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                    }
                } else {
                    if (BPCPQORG.ORG_STS == 'C' 
                        || BPCPQORG.ORG_STS == 'F') {
                        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
                        if (!JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                            WS_REC_CNT += 1;
                            BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                            R020_RTN_CHANGE_STS();
                            if (pgmRtn) return;
                            BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_REC_CNT);
    }
    public void B700_PAGE_DOWN_CALL() throws IOException,SQLException,Exception {
        BPCPQORG.BNK = WS_PARM_BNK;
        BPCPQORG.BR = WS_ORGM_KEY.WS_ORGM_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCSORGS.INP.STS == BPCPQORG.ORG_STS) {
            WS_COMMING_CNT += 1;
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
            if ((WS_COMMING_CNT > WS_PAGE_STRNO 
                && WS_COMMING_CNT <= WS_PAGE_ENDNO) 
                && !JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                WS_REC_CNT += 1;
                BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                R020_RTN_CHANGE_STS();
                if (pgmRtn) return;
                BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                CEP.TRC(SCCGWA, "SING");
                if (WS_COMMING_CNT == WS_PAGE_ENDNO) {
                    WS_OUT_FLG = 'Y';
                }
            }
        } else {
            if (BPCSORGS.INP.STS == 'CF' 
                || BPCSORGS.INP.STS == ' ') {
                if (BPCSORGS.INP.STS == ' ') {
                    WS_COMMING_CNT += 1;
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
                    if ((WS_COMMING_CNT > WS_PAGE_STRNO 
                        && WS_COMMING_CNT <= WS_PAGE_ENDNO) 
                        && !JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                        WS_REC_CNT += 1;
                        BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                        R020_RTN_CHANGE_STS();
                        if (pgmRtn) return;
                        BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                        if (WS_COMMING_CNT == WS_PAGE_ENDNO) {
                            WS_OUT_FLG = 'Y';
                        }
                    }
                } else {
                    if (BPCPQORG.ORG_STS == 'C' 
                        || BPCPQORG.ORG_STS == 'F') {
                        WS_COMMING_CNT += 1;
                        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_ORGM_KEY);
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ORGM_OLD_KEY);
                        if ((WS_COMMING_CNT > WS_PAGE_STRNO 
                            && WS_COMMING_CNT <= WS_PAGE_ENDNO) 
                            && !JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                            WS_REC_CNT += 1;
                            BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].BNO = WS_ORGM_KEY.WS_ORGM_BR;
                            R020_RTN_CHANGE_STS();
                            if (pgmRtn) return;
                            BPCSORGS.OUT.PAGE_ROW = (short) WS_REC_CNT;
                            if (WS_COMMING_CNT == WS_PAGE_ENDNO) {
                                WS_OUT_FLG = 'Y';
                            }
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_REC_CNT);
    }
    public void R010_CALC_ROW_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_COUNT_NO);
        WS_PAGE_ROW = WS_COUNT_NO % BPCSORGS.INP.PG_ROW;
        BPCSORGS.OUT.PAGE = (short) ((WS_COUNT_NO - WS_PAGE_ROW) / BPCSORGS.INP.PG_ROW);
        CEP.TRC(SCCGWA, BPCSORGS.OUT.PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        if (WS_PAGE_ROW > 0) {
            BPCSORGS.OUT.PAGE = (short) (BPCSORGS.OUT.PAGE + 1);
        }
    }
    public void R020_RTN_CHANGE_STS() throws IOException,SQLException,Exception {
        if (BPCPQORG.ORG_STS == 'O') {
            BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].LINE_FLG = '0';
        }
        if (BPCPQORG.ORG_STS == 'C') {
            BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].LINE_FLG = '2';
        }
        if (BPCPQORG.ORG_STS == 'F') {
            BPCSORGS.OUT.OUT_DATA[WS_REC_CNT-1].LINE_FLG = '3';
        }
    }
    public void T000_STARTBR_CN() throws IOException,SQLException,Exception {
        BPCRORGM.INFO.FUNC = 'S';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void T101_READNEXTBR_CN() throws IOException,SQLException,Exception {
        BPCRORGM.RETURN_INFO = ' ';
        BPCRORGM.INFO.FUNC = 'N';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        if (BPCRORGM.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCSORGS.RC);
            WS_FINIST = 'N';
        }
    }
    public void T101_READNEXTBR_VARYING_CN() throws IOException,SQLException,Exception {
        BPCRORGM.INFO.FUNC = 'N';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void T200_ENDBR_CN() throws IOException,SQLException,Exception {
        BPCRORGM.INFO.FUNC = 'E';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCPQORG.ORG_STS);
        CEP.TRC(SCCGWA, BPCPQORG.CALD_CD);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSORGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
