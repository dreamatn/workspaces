package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSGBAL {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFHIS_RD;
    brParm BPTFHIS_BR = new brParm();
    DBParm BPTFHIST_RD;
    brParm BPTFHIST_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP047";
    short K_OUTPUT_MAX = 30;
    String WS_AC = " ";
    String WS_TX_TOOL = " ";
    String WS_TX_CCY = " ";
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    double WS_BAL_C = 0;
    double WS_BAL_D = 0;
    String WS_BAL_CCY = " ";
    String WS_ERR_MSG = " ";
    char WS_DC_FLG = ' ';
    int WS_CNT_T = 0;
    int WS_CNT_H = 0;
    BPZSGBAL_WS_OUTPUT_TOD WS_OUTPUT_TOD = new BPZSGBAL_WS_OUTPUT_TOD();
    BPZSGBAL_WS_OUTPUT_HIS WS_OUTPUT_HIS = new BPZSGBAL_WS_OUTPUT_HIS();
    char WS_DATE_COND = ' ';
    char WS_REC_END_COND = ' ';
    BPCSWB02_AWA_0002 BPCSWB02_AWA_0002 = new BPCSWB02_AWA_0002();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRFHIS BPRFHIS = new BPRFHIS();
    SCCGWA SCCGWA;
    BPCSGBAL BPCSGBAL;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA, BPCSGBAL BPCSGBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSGBAL = BPCSGBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSGBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPRFHIS);
        SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_AP_MMO = SCCGWA.COMM_AREA.AP_MMO;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSGBAL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B000 BEGIN");
        B010_CHECK_PROG();
        if (pgmRtn) return;
        B030_GET_BAL();
        if (pgmRtn) return;
        B040_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSGBAL.INPUT.STR_DT);
        CEP.TRC(SCCGWA, BPCSGBAL.INPUT.END_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCSGBAL.INPUT.AC);
        CEP.TRC(SCCGWA, BPCSGBAL.INPUT.TX_TOOL);
        if (BPCSGBAL.INPUT.AC.trim().length() == 0 
            && BPCSGBAL.INPUT.TX_TOOL.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCSGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSGBAL.INPUT.STR_DT > BPCSGBAL.INPUT.END_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_GT_END, BPCSGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSGBAL.INPUT.STR_DT == 0 
            || BPCSGBAL.INPUT.END_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME, BPCSGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSGBAL.INPUT.STR_DT >= SCCGWA.COMM_AREA.AC_DATE) {
            WS_DATE_COND = 'T';
        } else if (BPCSGBAL.INPUT.END_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_DATE_COND = 'H';
        } else {
            WS_DATE_COND = 'B';
        }
        CEP.TRC(SCCGWA, WS_DATE_COND);
        WS_DC_FLG = BPCSGBAL.INPUT.DC_FLG;
    }
    public void B030_GET_BAL() throws IOException,SQLException,Exception {
        if (WS_DATE_COND == 'T') {
            S000_GROUP_BAL_TOD();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_TOD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGBAL.OUTPUT);
        } else if (WS_DATE_COND == 'H') {
            S000_GROUP_BAL_HIS();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_HIS);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGBAL.OUTPUT);
        } else if (WS_DATE_COND == 'B') {
            S000_GROUP_BAL_BOTH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCSGBAL.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZSGBAL INVALID FUNC(" + WS_DATE_COND + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSGBAL.OUTPUT;
        SCCFMT.DATA_LEN = 1114;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_GROUP_BAL_TOD() throws IOException,SQLException,Exception {
        WS_OUTPUT_TOD.WS_CNT_TOD = 1;
        WS_STR_DT = BPCSGBAL.INPUT.STR_DT;
        WS_END_DT = BPCSGBAL.INPUT.END_DT;
        WS_AC = BPCSGBAL.INPUT.AC;
        WS_TX_CCY = BPCSGBAL.INPUT.TX_CCY;
        WS_TX_TOOL = BPCSGBAL.INPUT.TX_TOOL;
        if (WS_TX_CCY.trim().length() > 0) {
            T000_GROUP_BPTFHIS();
            if (pgmRtn) return;
            WS_BAL_CCY = WS_TX_CCY;
            WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_CCY_TOD = WS_BAL_CCY;
            if (WS_DC_FLG == 'D') {
                WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_OUT_BAL_TOD = WS_BAL_D;
            } else if (WS_DC_FLG == 'C') {
                WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_IN_BAL_TOD = WS_BAL_C;
            } else {
                WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_OUT_BAL_TOD = WS_BAL_D;
                WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_IN_BAL_TOD = WS_BAL_C;
            }
        } else {
            WS_REC_END_COND = 'N';
            T000_STARTBR_GROUP_BPTFHIS();
            if (pgmRtn) return;
            T000_READNEXT_GROUP_BPTFHIS();
            if (pgmRtn) return;
            while (WS_REC_END_COND != 'Y' 
                && WS_CNT_T <= K_OUTPUT_MAX) {
                CEP.TRC(SCCGWA, WS_OUTPUT_TOD.WS_CNT_TOD);
                WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_CCY_TOD = WS_BAL_CCY;
                if (WS_DC_FLG == 'D') {
                    WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_OUT_BAL_TOD = WS_BAL_D;
                } else if (WS_DC_FLG == 'C') {
                    WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_IN_BAL_TOD = WS_BAL_C;
                } else {
                    WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_OUT_BAL_TOD = WS_BAL_D;
                    WS_OUTPUT_TOD.WS_BODY_TOD[WS_OUTPUT_TOD.WS_CNT_TOD-1].WS_IN_BAL_TOD = WS_BAL_C;
                }
                WS_OUTPUT_TOD.WS_CNT_TOD += 1;
                T000_READNEXT_GROUP_BPTFHIS();
                if (pgmRtn) return;
            }
            T000_ENDBR_GROUP_BPTFHIS();
            if (pgmRtn) return;
            WS_OUTPUT_TOD.WS_CNT_TOD = (short) (WS_OUTPUT_TOD.WS_CNT_TOD - 1);
        }
    }
    public void S000_GROUP_BAL_HIS() throws IOException,SQLException,Exception {
        WS_OUTPUT_HIS.WS_CNT_HIS = 1;
        WS_STR_DT = BPCSGBAL.INPUT.STR_DT;
        WS_END_DT = BPCSGBAL.INPUT.END_DT;
        WS_AC = BPCSGBAL.INPUT.AC;
        WS_TX_CCY = BPCSGBAL.INPUT.TX_CCY;
        if (WS_TX_CCY.trim().length() > 0) {
            T000_GROUP_BPTFHIST();
            if (pgmRtn) return;
            WS_BAL_CCY = WS_TX_CCY;
            WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_CCY_HIS = WS_BAL_CCY;
            if (WS_DC_FLG == 'D') {
                WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_OUT_BAL_HIS = WS_BAL_D;
            } else if (WS_DC_FLG == 'C') {
                WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_IN_BAL_HIS = WS_BAL_C;
            } else {
                WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_OUT_BAL_HIS = WS_BAL_D;
                WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_IN_BAL_HIS = WS_BAL_C;
            }
        } else {
            WS_REC_END_COND = 'N';
            T000_STARTBR_GROUP_BPTFHIST();
            if (pgmRtn) return;
            T000_READNEXT_GROUP_BPTFHIST();
            if (pgmRtn) return;
            while (WS_REC_END_COND != 'Y' 
                && WS_CNT_T <= K_OUTPUT_MAX) {
                CEP.TRC(SCCGWA, WS_OUTPUT_HIS.WS_CNT_HIS);
                WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_CCY_HIS = WS_BAL_CCY;
                if (WS_DC_FLG == 'D') {
                    WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_OUT_BAL_HIS = WS_BAL_D;
                } else if (WS_DC_FLG == 'C') {
                    WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_IN_BAL_HIS = WS_BAL_C;
                } else {
                    WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_OUT_BAL_HIS = WS_BAL_D;
                    WS_OUTPUT_HIS.WS_BODY_HIS[WS_OUTPUT_HIS.WS_CNT_HIS-1].WS_IN_BAL_HIS = WS_BAL_C;
                }
                WS_OUTPUT_HIS.WS_CNT_HIS += 1;
                T000_READNEXT_GROUP_BPTFHIST();
                if (pgmRtn) return;
            }
            T000_ENDBR_GROUP_BPTFHIST();
            if (pgmRtn) return;
            WS_OUTPUT_HIS.WS_CNT_HIS = (short) (WS_OUTPUT_HIS.WS_CNT_HIS - 1);
        }
    }
    public void S000_GROUP_BAL_BOTH() throws IOException,SQLException,Exception {
        S000_GROUP_BAL_TOD();
        if (pgmRtn) return;
        S000_GROUP_BAL_HIS();
        if (pgmRtn) return;
        if (WS_OUTPUT_TOD.WS_CNT_TOD == 0 
                && WS_OUTPUT_HIS.WS_CNT_HIS != 0) {
            CEP.TRC(SCCGWA, "ONLY_TOD");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_HIS);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGBAL.OUTPUT);
        } else if (WS_OUTPUT_HIS.WS_CNT_HIS == 0 
                && WS_OUTPUT_TOD.WS_CNT_TOD != 0) {
            CEP.TRC(SCCGWA, "ONLY_HIS");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_TOD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGBAL.OUTPUT);
        } else if (WS_OUTPUT_HIS.WS_CNT_HIS != 0 
                && WS_OUTPUT_TOD.WS_CNT_TOD != 0) {
            CEP.TRC(SCCGWA, "BOTH_TOD_HIS");
            WS_CNT_T = 1;
            while (WS_CNT_T <= WS_OUTPUT_TOD.WS_CNT_TOD) {
                WS_CNT_H = 1;
                WS_REC_END_COND = 'N';
                while (WS_CNT_H <= WS_OUTPUT_HIS.WS_CNT_HIS 
                    && WS_REC_END_COND != 'Y') {
                    if (WS_OUTPUT_TOD.WS_BODY_TOD[WS_CNT_T-1].WS_CCY_TOD.equalsIgnoreCase(WS_OUTPUT_HIS.WS_BODY_HIS[WS_CNT_H-1].WS_CCY_HIS)) {
                        S000_CAL_BAL_BOTH();
                        if (pgmRtn) return;
                        WS_REC_END_COND = 'Y';
                    }
                    WS_CNT_H += 1;
                }
                if (WS_REC_END_COND == 'N') {
                    WS_OUTPUT_HIS.WS_CNT_HIS += 1;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_TOD.WS_BODY_TOD[WS_CNT_H-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_OUTPUT_HIS.WS_BODY_HIS[WS_CNT_H-1]);
                }
                WS_CNT_T += 1;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_HIS);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGBAL.OUTPUT);
        } else {
        }
    }
    public void S000_CAL_BAL_BOTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT_H);
        CEP.TRC(SCCGWA, WS_CNT_T);
        WS_OUTPUT_HIS.WS_BODY_HIS[WS_CNT_H-1].WS_IN_BAL_HIS = WS_OUTPUT_TOD.WS_BODY_TOD[WS_CNT_T-1].WS_IN_BAL_TOD + WS_OUTPUT_HIS.WS_BODY_HIS[WS_CNT_H-1].WS_IN_BAL_HIS;
        WS_OUTPUT_HIS.WS_BODY_HIS[WS_CNT_H-1].WS_OUT_BAL_HIS = WS_OUTPUT_TOD.WS_BODY_TOD[WS_CNT_T-1].WS_OUT_BAL_TOD + WS_OUTPUT_HIS.WS_BODY_HIS[WS_CNT_H-1].WS_OUT_BAL_HIS;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_GROUP_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIS);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_AC);
        if (WS_TX_TOOL.trim().length() > 0) {
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0)";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else {
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0)";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_C);
    }
    public void T000_STARTBR_GROUP_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_AC);
        if (WS_TX_TOOL.trim().length() > 0) {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS_BR.rp.grp = "TX_CCY";
            BPTFHIS_BR.rp.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0),WS-BAL-CCY=TX_CCY";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS_BR.rp.grp = "TX_CCY";
            BPTFHIS_BR.rp.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0),WS-BAL-CCY=TX_CCY";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_GROUP_BPTFHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_END_COND = 'Y';
        }
    }
    public void T000_ENDBR_GROUP_BPTFHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_GROUP_BPTFHIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIS);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_AC);
        if (WS_TX_TOOL.trim().length() > 0) {
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIST_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0)";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        } else {
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIST_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0)";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_C);
    }
    public void T000_STARTBR_GROUP_BPTFHIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_AC);
        if (WS_TX_TOOL.trim().length() > 0) {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIST_BR.rp.grp = "TX_CCY";
            BPTFHIST_BR.rp.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0),WS-BAL-CCY=TX_CCY";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIST_BR.rp.grp = "TX_CCY";
            BPTFHIST_BR.rp.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,0)),0),WS-BAL-C=NVL(SUM(DECODE(DRCRFLG,'C',TX_AMT,0)),0),WS-BAL-CCY=TX_CCY";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_GROUP_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_END_COND = 'Y';
        }
    }
    public void T000_ENDBR_GROUP_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
