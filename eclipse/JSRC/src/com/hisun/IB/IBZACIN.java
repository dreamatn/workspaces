package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACIN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    char K_CLOSE = 'C';
    char K_INQUIRE = 'I';
    char K_MODIFY = 'M';
    char K_STS_INQ = 'Q';
    char K_STS_MOD = 'U';
    char K_RATE_BR = 'B';
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    String K_OUTPUT_FMT_1 = "IBA11";
    String K_OUTPUT_FMT_2 = "IBA51";
    String WS_TABLE_NAME = " ";
    double WS_BUD_INT = 0;
    IBZACIN_WS_INTS_DT WS_INTS_DT = new IBZACIN_WS_INTS_DT();
    short WS_SETT_DT = 0;
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBCRODE IBCRODE = new IBCRODE();
    CICCUST CICCUST = new CICCUST();
    IBCQINF IBCQINF = new IBCQINF();
    BPCQCCY BPCQCCY = new BPCQCCY();
    IBCOACSI IBCOACSI = new IBCOACSI();
    IBCFA11 IBCFA11 = new IBCFA11();
    DDCSLLCX DDCSLLCX = new DDCSLLCX();
    DDCOLLCX DDCOLLCX = new DDCOLLCX();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACIN IBCACIN;
    public void MP(SCCGWA SCCGWA, IBCACIN IBCACIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACIN = IBCACIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCACIN.RC.RC_MMO = " ";
        IBCACIN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_AC_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACIN.FUNC);
        CEP.TRC(SCCGWA, IBCACIN.NOS_CD);
        CEP.TRC(SCCGWA, IBCACIN.CCY);
        CEP.TRC(SCCGWA, IBCACIN.AC_NO);
        if ((IBCACIN.NOS_CD.trim().length() == 0 
            || IBCACIN.CCY.trim().length() == 0) 
            && IBCACIN.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCACIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCACIN.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCACIN.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCACIN.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCACIN.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 706660500 
            || IBCACIN.FUNC == K_RATE_BR) {
        } else {
            if (IBCQINF.OUTPUT_DATA.POST_ACT_CTR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                && (IBCACIN.FUNC == K_INQUIRE 
                || IBCACIN.FUNC == K_STS_INQ)) {
                B020_01_GET_AC_INFO();
                if (pgmRtn) return;
            }
            if ((IBCQINF.OUTPUT_DATA.AC_STS != 'N' 
                && (IBCACIN.FUNC == K_MODIFY 
                || IBCACIN.FUNC == K_CLOSE)) 
                || (IBCQINF.OUTPUT_DATA.AC_STS == 'C' 
                && IBCACIN.FUNC == K_STS_MOD)) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCACIN.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INQ_NOT_ALLOW, IBCACIN.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if ((BPCPQORG.VIL_TYP.equalsIgnoreCase("51") 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706670500) 
                || (BPCPQORG.VIL_TYP.equalsIgnoreCase("52") 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706671500) 
                || (BPCPQORG.VIL_TYP.equalsIgnoreCase("53") 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706672500) 
                || (BPCPQORG.VIL_TYP.equalsIgnoreCase("54") 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706673500)) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INQ_NOT_ALLOW, IBCACIN.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (IBCACIN.FUNC == K_STS_MOD 
            || IBCACIN.FUNC == K_STS_INQ) {
            IBS.init(SCCGWA, IBCOACSI);
            IBCOACSI.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
            IBCOACSI.NOS_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
            IBCOACSI.CCY = IBCQINF.INPUT_DATA.CCY;
            IBCOACSI.CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
            IBCOACSI.AC_STS = IBCQINF.OUTPUT_DATA.AC_STS;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_OUTPUT_FMT_2;
            SCCFMT.DATA_PTR = IBCOACSI;
            SCCFMT.DATA_LEN = 311;
            IBS.FMT(SCCGWA, SCCFMT);
        } else {
            IBS.init(SCCGWA, IBCFA11);
            IBCFA11.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
            IBCFA11.CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
            IBCFA11.NOS_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
            IBCFA11.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
            IBCFA11.CCY = IBCQINF.INPUT_DATA.CCY;
            IBCFA11.PROD_CD = IBCQINF.OUTPUT_DATA.PROD_CD;
            IBCFA11.POST_CTR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            IBCFA11.OIC_NO = IBCQINF.OUTPUT_DATA.OIC_NO;
            IBCFA11.RESP_CD = IBCQINF.OUTPUT_DATA.RESP_CD;
            IBCFA11.SUB_DP = IBCQINF.OUTPUT_DATA.SUB_DP;
            IBCFA11.AC_ATTR = IBCQINF.OUTPUT_DATA.AC_ATTR;
            IBCFA11.FUND_ATTR = IBCQINF.OUTPUT_DATA.FUND_ATTR;
            IBCFA11.SWR_BR_FLG = IBCQINF.OUTPUT_DATA.SWR_BR;
            IBCFA11.BAL_AMT = IBCQINF.OUTPUT_DATA.VALUE_BAL;
            IBCFA11.L_BAL_AMT = IBCQINF.OUTPUT_DATA.L_VALUE_BAL;
            IBCFA11.HLD_AMT = IBCQINF.OUTPUT_DATA.HLD_AMT;
            IBCFA11.CORRAC_BK = IBCQINF.OUTPUT_DATA.CORRAC_BK;
            IBCFA11.CORRAC_NO = IBCQINF.OUTPUT_DATA.CORRAC_NO;
            IBCFA11.PRV_FLAG = IBCQINF.OUTPUT_DATA.PRV_FLAG;
            IBCFA11.RATE_FLAG = IBCQINF.OUTPUT_DATA.RATE_FLAG;
            IBCFA11.OD_FLAG = IBCQINF.OUTPUT_DATA.OD_FLAG;
            IBCFA11.OD_RATE_FLAG = IBCQINF.OUTPUT_DATA.OD_RATE_FLAG;
            IBCFA11.OD_PAY_AC = IBCQINF.OUTPUT_DATA.OD_PAY_AC;
            if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'N') {
                IBCFA11.RATE_MTH = IBCQINF.OUTPUT_DATA.RATE_MTH;
                IBCFA11.RATE_TYPE = IBCQINF.OUTPUT_DATA.RATE_TYPE;
                IBCFA11.RATE_TERM = IBCQINF.OUTPUT_DATA.RATE_TERM;
                IBCFA11.RATE_PCT = IBCQINF.OUTPUT_DATA.RATE_PCT;
                IBCFA11.RATE_SPREAD = IBCQINF.OUTPUT_DATA.RATE_SPREAD;
                IBCFA11.RATE = IBCQINF.OUTPUT_DATA.RATE;
                IBCFA11.INTS_DT_MM = IBCQINF.OUTPUT_DATA.INTS_DT_MM;
                IBCFA11.INTS_DT_DD = IBCQINF.OUTPUT_DATA.INTS_DT_DD;
                IBCFA11.INTS_CYC = IBCQINF.OUTPUT_DATA.INTS_CYC;
                if (IBCQINF.OUTPUT_DATA.CALR_STD == K_360_DAYS) {
                    IBCFA11.CALR_STD = "01";
                } else if (IBCQINF.OUTPUT_DATA.CALR_STD == K_365_DAYS) {
                    IBCFA11.CALR_STD = "02";
                } else if (IBCQINF.OUTPUT_DATA.CALR_STD == K_366_DAYS) {
                    IBCFA11.CALR_STD = "03";
                }
            } else {
                B030_01_GET_L_INFO();
                if (pgmRtn) return;
            }
            IBCFA11.L_INTS_DATE = IBCQINF.OUTPUT_DATA.L_INTS_DATE;
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.BUD_INT);
            IBCFA11.ESET_AMT = IBCQINF.OUTPUT_DATA.BUD_INT;
            B030_02_ROUND_INT();
            if (pgmRtn) return;
            IBCFA11.BUD_INT = IBCRODE.INT_AMT;
            CEP.TRC(SCCGWA, IBCFA11.BUD_INT);
            IBCFA11.EFFDATE = IBCQINF.OUTPUT_DATA.EFF_DATE;
            IBCFA11.OPENDATE = IBCQINF.OUTPUT_DATA.OPEN_DATE;
            CEP.TRC(SCCGWA, IBCFA11.EFFDATE);
            CEP.TRC(SCCGWA, IBCFA11.OPENDATE);
            IBCFA11.OPEN_TLR = IBCQINF.OUTPUT_DATA.OPEN_TLR;
            IBCFA11.AUTH_TLR = IBCQINF.OUTPUT_DATA.AUTH_TLR;
            IBCFA11.UPD_DATE = IBCQINF.OUTPUT_DATA.LAST_MAINT_DATE;
            IBCFA11.LAST_DATE = IBCQINF.OUTPUT_DATA.LAST_FI_DATE;
            IBCFA11.AC_STS = IBCQINF.OUTPUT_DATA.AC_STS;
            IBCFA11.AC_NATR = IBCQINF.OUTPUT_DATA.AC_NATR;
            IBCFA11.VALUE_TAX = IBCQINF.OUTPUT_DATA.VALUE_TAX;
            IBCFA11.UPD_TLT = IBCQINF.OUTPUT_DATA.L_MAINT_TLR;
            CEP.TRC(SCCGWA, IBCFA11.CON_RATE);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_OUTPUT_FMT_1;
            SCCFMT.DATA_PTR = IBCFA11;
            SCCFMT.DATA_LEN = 687;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void B030_01_GET_L_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLLCX);
        DDCSLLCX.FUNC = 'L';
        DDCSLLCX.AC = IBCQINF.OUTPUT_DATA.CORRAC_NO;
        CEP.TRC(SCCGWA, DDCSLLCX.AC);
        S000_CALL_DDCSLLCX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_TYPE);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        CEP.TRC(SCCGWA, DDCSLLCX.ADP_POST_DT);
        CEP.TRC(SCCGWA, DDCSLLCX.CON_RATE);
        CEP.TRC(SCCGWA, DDCSLLCX.ADP_POST_PERIOD);
        if (DDCSLLCX.TIR_TYPE == 'N') {
            if (DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 != 0) {
                IBCFA11.RATE_MTH = '1';
                IBCFA11.RATE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1;
            } else {
                IBCFA11.RATE_MTH = '2';
                IBCFA11.RATE_TYPE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1;
                IBCFA11.RATE_TERM = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1;
                IBCFA11.RATE_PCT = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1;
                IBCFA11.RATE_SPREAD = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1;
            }
            IBCFA11.CON_RATE = DDCSLLCX.CON_RATE;
            if (DDCSLLCX.ADP_POST_PERIOD == 'Y') {
                IBCFA11.INTS_CYC = '1';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'H') {
                IBCFA11.INTS_CYC = '2';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'Q') {
                IBCFA11.INTS_CYC = '3';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'M') {
                IBCFA11.INTS_CYC = '4';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'D') {
                IBCFA11.INTS_CYC = '5';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'T') {
                IBCFA11.INTS_CYC = '6';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'B') {
                IBCFA11.INTS_CYC = '7';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'W') {
                IBCFA11.INTS_CYC = '8';
            }
            WS_SETT_DT = (short) DDCSLLCX.ADP_POST_DT;
            CEP.TRC(SCCGWA, WS_SETT_DT);
            IBS.CPY2CLS(SCCGWA, WS_SETT_DT+"", WS_INTS_DT);
            CEP.TRC(SCCGWA, WS_INTS_DT);
            IBCFA11.INTS_DT_MM = WS_INTS_DT.WS_INTS_MM;
            IBCFA11.INTS_DT_DD = WS_INTS_DT.WS_INTS_DD;
        }
        CEP.TRC(SCCGWA, DDCSLLCX.OD_CON_RATE);
        IBCFA11.OD_RATE = DDCSLLCX.OD_CON_RATE;
        IBCFA11.OD_INTS_CYC = DDCSLLCX.OD_INTS_CYC;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = IBCQINF.INPUT_DATA.CCY;
        S000_CALL_SCSOCCY();
        if (pgmRtn) return;
        IBCFA11.CALR_STD = BPCQCCY.DATA.CALR_STD;
        CEP.TRC(SCCGWA, IBCFA11.RATE_MTH);
        CEP.TRC(SCCGWA, IBCFA11.RATE);
        CEP.TRC(SCCGWA, IBCFA11.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCFA11.RATE_TERM);
        CEP.TRC(SCCGWA, IBCFA11.RATE_SPREAD);
        CEP.TRC(SCCGWA, IBCFA11.CON_RATE);
        CEP.TRC(SCCGWA, WS_INTS_DT);
        CEP.TRC(SCCGWA, IBCFA11.INTS_DT_MM);
        CEP.TRC(SCCGWA, IBCFA11.INTS_DT_DD);
    }
    public void B030_02_ROUND_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCRODE);
        IBCRODE.INT_AMT = IBCQINF.OUTPUT_DATA.BUD_INT;
        IBCRODE.CCY = IBCQINF.INPUT_DATA.CCY;
        S000_CALL_IBZRODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
    }
    public void S000_LINK_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0 
            && CICCUST.RC.RC_CODE != 3011) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDCSLLCX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-AC-LLCX", DDCSLLCX);
    }
    public void S000_CALL_SCSOCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZRODE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ROUND-INT", IBCRODE);
        if (IBCRODE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRODE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
