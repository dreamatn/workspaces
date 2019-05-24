package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS8820 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM110";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    CMZS8820_WS_BATH_PARM WS_BATH_PARM = new CMZS8820_WS_BATH_PARM();
    char WS_END_FLG = ' ';
    String WS_AC_NO = " ";
    String WS_CI_NM = " ";
    String WS_ID_NO = " ";
    String WS_ID_TYP = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CMRBSP20 CMRBSP20 = new CMRBSP20();
    CICOPDCP CICOPDCP = new CICOPDCP();
    DCCSSPOT DCCSSPOT = new DCCSSPOT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    TDCKHCR TDCKHCR = new TDCKHCR();
    CICCUST CICCUST = new CICCUST();
    TDCOTZZC TDCOTZZC = new TDCOTZZC();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBATH SCCBATH;
    CMCS8820 CMCS8820;
    public void MP(SCCGWA SCCGWA, CMCS8820 CMCS8820) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS8820 = CMCS8820;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS8820 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, "222");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "333");
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        CEP.TRC(SCCGWA, "444");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCS8820.OPEN_TYPE);
        CEP.TRC(SCCGWA, CMCS8820.CI_NO);
        if (CMCS8820.OPEN_TYPE.equalsIgnoreCase("101")
            || CMCS8820.OPEN_TYPE.equalsIgnoreCase("301")) {
            B200_DD_OPENAC_PROC();
            if (pgmRtn) return;
            B300_ACCOUNT_TRANSFER();
            if (pgmRtn) return;
        } else if (CMCS8820.OPEN_TYPE.equalsIgnoreCase("201")
            || CMCS8820.OPEN_TYPE.equalsIgnoreCase("202")
            || CMCS8820.OPEN_TYPE.equalsIgnoreCase("203")) {
            B500_TD_OPENAC_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CMCS8820.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CMCS8820.NAME.trim().length() > 0 
            && !CMCS8820.NAME.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_NM_ERR);
        }
        if (CMCS8820.ID_TYPE.trim().length() > 0 
            && !CMCS8820.ID_TYPE.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDTYP_ERR);
        }
        if (CMCS8820.ID_NO.trim().length() > 0 
            && !CMCS8820.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDNO_ERR);
        }
        if (CMCS8820.NAME.trim().length() > 0) {
            WS_CI_NM = CMCS8820.NAME;
        } else {
            WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
        }
        if (CMCS8820.ID_TYPE.trim().length() > 0) {
            WS_ID_TYP = CMCS8820.ID_TYPE;
        } else {
            WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        }
        if (CMCS8820.ID_NO.trim().length() > 0) {
            WS_ID_NO = CMCS8820.ID_NO;
        } else {
            WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        }
    }
    public void B200_DD_OPENAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUOPAC);
        CEP.TRC(SCCGWA, "OPEN AC");
        CEP.TRC(SCCGWA, CMCS8820.CI_NO);
        IBS.init(SCCGWA, DDCUOPAC);
        if (CMCS8820.OPEN_TYPE.equalsIgnoreCase("301")) {
            DDCUOPAC.SOCIAL_CARD_FLG = '8';
            DDCUOPAC.AC_TYP = 'A';
            DDCUOPAC.DRAW_MTH = '5';
        } else {
            DDCUOPAC.DRAW_MTH = '1';
        }
        DDCUOPAC.CI_NO = CMCS8820.CI_NO;
        DDCUOPAC.PROD_CODE = CMCS8820.PROD_CD;
        DDCUOPAC.CCY = CMCS8820.CCY;
        DDCUOPAC.AC_TYP = 'A';
        DDCUOPAC.CCY_TYPE = '1';
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '2';
        DDCUOPAC.PSWD = CMCS8820.PSW;
        DDCUOPAC.CI_CNM = WS_CI_NM;
        DDCUOPAC.REMARK = CMCS8820.SMR;
        DDCUOPAC.AC_TYPE = '1';
        DDCUOPAC.ACNO_FLG = 'C';
        S000_CALL_DDZUOPAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        WS_AC_NO = DDCUOPAC.AC;
        CMCS8820.CUSAC = WS_AC_NO;
        CEP.TRC(SCCGWA, CMCS8820.CUSAC);
    }
    public void B300_ACCOUNT_TRANSFER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS8820.UNIT_AC);
        CEP.TRC(SCCGWA, CMCS8820.BV_TYP);
        CEP.TRC(SCCGWA, CMCS8820.AMT);
        CEP.TRC(SCCGWA, WS_AC_NO);
        IBS.init(SCCGWA, DDCSTRAC);
        DDCSTRAC.FR_BV_TYPE = '3';
        DDCSTRAC.FR_AC = CMCS8820.UNIT_AC;
        DDCSTRAC.FR_CCY = CMCS8820.CCY;
        DDCSTRAC.FR_AMT = CMCS8820.AMT;
        DDCSTRAC.TO_BV_TYPE = '2';
        DDCSTRAC.TO_AC = WS_AC_NO;
        DDCSTRAC.TO_CCY = CMCS8820.CCY;
        DDCSTRAC.TO_AMT = CMCS8820.AMT;
        DDCSTRAC.DR_MMO = CMCS8820.BUS_RMK;
        if (CMCS8820.AMT != 0) {
            S000_CALL_DDZSTRAC();
            if (pgmRtn) return;
        }
    }
    public void B500_TD_OPENAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS8820.AMT);
        IBS.init(SCCGWA, TDCKHCR);
        TDCKHCR.BVPRT_FLG = 'N';
        TDCKHCR.MAIN_FLG = 'N';
        CEP.TRC(SCCGWA, TDCKHCR.MPROD_CD);
        CEP.TRC(SCCGWA, CMCS8820.PROD_CD);
        TDCKHCR.BVPRT_FLG = 'N';
        TDCKHCR.DRAW_MTH = '1';
        TDCKHCR.CT_FLG = '1';
        TDCKHCR.PROD_CD = CMCS8820.PROD_CD;
        if (CMCS8820.BV_TYP == '1') {
            TDCKHCR.BV_CD = "020";
        } else if (CMCS8820.BV_TYP == '2') {
            TDCKHCR.BV_CD = "001";
        } else if (CMCS8820.BV_TYP == '3') {
            TDCKHCR.BV_CD = "002";
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_BVT_ERR);
        }
        TDCKHCR.BV_TYP = CMCS8820.BV_TYP;
        TDCKHCR.AC_NAME = WS_CI_NM;
        TDCKHCR.ID_TYP = WS_ID_TYP;
        TDCKHCR.ID_NO = WS_ID_NO;
        TDCKHCR.CI_NO = CMCS8820.CI_NO;
        TDCKHCR.MMO = CMCS8820.BUS_RMK;
        TDCKHCR.CCY = CMCS8820.CCY;
        TDCKHCR.CCY_TYP = '1';
        TDCKHCR.TXN_AMT = CMCS8820.AMT;
        TDCKHCR.TERM = CMCS8820.TERM;
        TDCKHCR.PSW = CMCS8820.PSW;
        TDCKHCR.D_INF = CMCS8820.PSW;
        TDCKHCR.CROS_CR_FLG = CMCS8820.CROS_CR_FLG;
        TDCKHCR.CROS_DR_FLG = CMCS8820.CROS_DR_FLG;
        TDCKHCR.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        TDCKHCR.OPP_AC = CMCS8820.UNIT_AC;
        TDCKHCR.INT_SEL = CMCS8820.RATE_FLG;
        TDCKHCR.SPRD_PCT = CMCS8820.FLT_RAT;
        TDCKHCR.SPRD_PNT = CMCS8820.PCT_S;
        TDCKHCR.INSTR_MTH = CMCS8820.INSRT_MTH;
        S000_CALL_TDZKHCR();
        if (pgmRtn) return;
        WS_AC_NO = TDCKHCR.AC;
        CMCS8820.CUSAC = TDCKHCR.AC;
        CMCS8820.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMCS8820.EXP_DATE = TDCKHCR.EXP_DT;
        CMCS8820.INT_RAT = TDCKHCR.INT_RAT;
        CMCS8820.INSRT_MTH = TDCKHCR.INSTR_MTH;
        CEP.TRC(SCCGWA, CMCS8820.CUSAC);
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCS8820;
        SCCFMT.DATA_LEN = 1183;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST, true);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC", DDCUOPAC, true);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA, true);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZKHCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-KH-CR", TDCKHCR, true);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
