package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSIBAL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTIACCY_RD;
    DBParm DDTMST_RD;
    DBParm DDTINTB_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD803";
    String WS_ERR_MSG = " ";
    double WS_CURR_BAL = 0;
    double WS_AVL_BAL = 0;
    double WS_HOLD_BAL = 0;
    double WS_DEP_INT = 0;
    double WS_OD_INT = 0;
    double WS_UOD_INT = 0;
    double WS_INT_BAL = 0;
    double WS_NOT_INT_BAL = 0;
    double WS_DRAW_AMT = 0;
    String WS_AC_ENM = " ";
    String WS_AC_CNM = " ";
    double WS_DT_CAMT = 0;
    double WS_DT_AMT = 0;
    double WS_AVL_BAL1 = 0;
    String WS_AC_NO = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDCIINTB DDCIINTB = new DDCIINTB();
    DDCOIBAL DDCOIBAL = new DDCOIBAL();
    CICACCU CICACCU = new CICACCU();
    DDRINTB DDRINTB = new DDRINTB();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICCUST CICCUST = new CICCUST();
    DDCUINQV DDCUINQV = new DDCUINQV();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCSIBAL DDCSIBAL;
    public void MP(SCCGWA SCCGWA, DDCSIBAL DDCSIBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSIBAL = DDCSIBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSIBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_CI_CITACR_PROC();
        if (pgmRtn) return;
        B030_GET_DD_BAL();
        if (pgmRtn) return;
        B050_GET_CI_INFO();
        if (pgmRtn) return;
        if (DDCSIBAL.FUNC != 'C') {
            B070_FMT_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "**********INPUT DATA:");
        CEP.TRC(SCCGWA, DDCSIBAL.AC_NO);
        CEP.TRC(SCCGWA, DDCSIBAL.CCY);
        CEP.TRC(SCCGWA, DDCSIBAL.CCY_TYPE);
        CEP.TRC(SCCGWA, "*********************");
        if (DDCSIBAL.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSIBAL.CCY_TYPE);
        if (DDCSIBAL.CCY.equalsIgnoreCase("156") 
            && DDCSIBAL.CCY_TYPE == ' ') {
            DDCSIBAL.CCY_TYPE = '1';
        }
        CEP.TRC(SCCGWA, DDCSIBAL.CCY_TYPE);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSIBAL.AC_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC") 
            && CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() == 0) {
            DDCSIBAL.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
        }
    }
    public void B020_GET_CI_CITACR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSIBAL.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCSIBAL.CCY;
        CICQACAC.DATA.CR_FLG = DDCSIBAL.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_STS == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
    }
    public void B030_GET_DD_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1111");
            WS_CURR_BAL = 0;
            WS_AVL_BAL = 0;
            WS_NOT_INT_BAL = 0;
            WS_HOLD_BAL = 0;
            WS_INT_BAL = 0;
            WS_DRAW_AMT = 0;
        } else {
            WS_CURR_BAL = DDRCCY.CURR_BAL;
            WS_NOT_INT_BAL = DDRCCY.NOT_INT_BAL;
            WS_HOLD_BAL = DDRCCY.HOLD_BAL;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, DDRCCY.LAST_BAL_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, DDRCCY.LAST_BAL_DATE);
            WS_DT_CAMT = DDRCCY.DRW_CAMT + DDRCCY.DRW_TAMT;
            CEP.TRC(SCCGWA, WS_DT_CAMT);
            CEP.TRC(SCCGWA, DDRCCY.DRW_CAMT);
            CEP.TRC(SCCGWA, DDRCCY.DRW_TAMT);
            CEP.TRC(SCCGWA, WS_DRAW_AMT);
            WS_DT_AMT = DDRCCY.DRW_CAMT;
            WS_DRAW_AMT = WS_DT_CAMT;
            WS_AVL_BAL = WS_CURR_BAL - WS_HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AVL_BAL = 0;
            }
            if (WS_AVL_BAL < 0) {
                WS_AVL_BAL = 0;
            }
            WS_INT_BAL = WS_CURR_BAL - WS_NOT_INT_BAL;
            CEP.TRC(SCCGWA, "123456789");
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
            DDCSIBAL.CURR_BAL = DDRCCY.CURR_BAL;
            DDCSIBAL.AVL_BAL = WS_AVL_BAL;
            DDCSIBAL.NOT_INT_BAL = WS_NOT_INT_BAL;
            DDCSIBAL.HOLD_BAL = WS_HOLD_BAL;
            DDCSIBAL.INT_BAL = WS_INT_BAL;
        }
        CEP.TRC(SCCGWA, WS_AVL_BAL);
        CEP.TRC(SCCGWA, WS_AVL_BAL1);
        CEP.TRC(SCCGWA, DDCSIBAL.AVL_BAL);
        CEP.TRC(SCCGWA, WS_CURR_BAL);
        CEP.TRC(SCCGWA, WS_HOLD_BAL);
        CEP.TRC(SCCGWA, WS_INT_BAL);
        CEP.TRC(SCCGWA, WS_DRAW_AMT);
        IBS.init(SCCGWA, DDCIINTB);
        IBS.init(SCCGWA, DDRINTB);
        if (DDRMST.AC_STS == 'M') {
        } else {
            DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, "00000000000000000");
            CEP.TRC(SCCGWA, DDCSIBAL.AC_NO);
            CEP.TRC(SCCGWA, DDCSIBAL.CCY);
            CEP.TRC(SCCGWA, DDCSIBAL.CCY_TYPE);
            T000_READ_DDTINTB();
            if (pgmRtn) return;
        }
    }
    public void B050_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM.trim().length() > 0) {
            WS_AC_CNM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        } else {
            WS_AC_CNM = CICCUST.O_DATA.O_CI_NM;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM.trim().length() > 0) {
            WS_AC_ENM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        } else {
            WS_AC_ENM = CICCUST.O_DATA.O_CI_ENM;
        }
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANSFER_OUTPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = DDCOIBAL;
        SCCFMT.DATA_LEN = 694;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHK_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSIBAL.AC_NO;
        DCCPACTY.INPUT.FUNC = '1';
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDCSIBAL.AC_NO = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        CEP.TRC(SCCGWA, DDCSIBAL.AC_NO);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
    }
    public void R000_TRANSFER_OUTPUT_DATA() throws IOException,SQLException,Exception {
        DDCOIBAL.AC_NO = DDCSIBAL.AC_NO;
        DDCOIBAL.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCOIBAL.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        DDCOIBAL.CI_NO = CICCUST.O_DATA.O_CI_NO;
        DDCOIBAL.CNAME = WS_AC_CNM;
        DDCOIBAL.ENAME = WS_AC_ENM;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_FREE_TYPE);
        if (DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("004")) {
            IBS.init(SCCGWA, DDCUINQV);
            DDCUINQV.INPUT.VS_AC = WS_AC_NO;
            DDCUINQV.INPUT.CCY = DDCSIBAL.CCY;
            DDCUINQV.INPUT.CCY_TYP = DDCSIBAL.CCY_TYPE;
            S000_CALL_DDZUINQV();
            if (pgmRtn) return;
            DDCOIBAL.AVAIL_BAL = DDCUINQV.OUTPUT.VS_CURR_BAL;
        } else {
            DDCOIBAL.AVAIL_BAL = DDCSIBAL.AVL_BAL;
        }
        WS_CURR_BAL = WS_CURR_BAL + DDRCCY.CCAL_TOT_BAL;
        DDCOIBAL.LEDGER_BAL = WS_CURR_BAL;
        DDCOIBAL.HOLD_BAL = WS_HOLD_BAL;
        if (DDRCCY.LAST_FN_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            DDCOIBAL.TODAY_TOTAL_DRAW_AMT = WS_DRAW_AMT;
            DDCOIBAL.TODAY_TOTAL_AMT = WS_DT_AMT;
        } else {
            if ("0".trim().length() == 0) DDCOIBAL.TODAY_TOTAL_DRAW_AMT = 0;
            else DDCOIBAL.TODAY_TOTAL_DRAW_AMT = Double.parseDouble("0");
            if ("0".trim().length() == 0) DDCOIBAL.TODAY_TOTAL_AMT = 0;
            else DDCOIBAL.TODAY_TOTAL_AMT = Double.parseDouble("0");
        }
        CEP.TRC(SCCGWA, DDRMST.LAST_FN_DATE);
        CEP.TRC(SCCGWA, DDRMST.LAST_FN_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_DRAW_AMT);
        CEP.TRC(SCCGWA, DDCOIBAL.TODAY_TOTAL_DRAW_AMT);
        DDCOIBAL.CR_INT = DDRINTB.DEP_ACCU_INT + DDRINTB.DEP_ADJ_INT;
        DDCOIBAL.DR_INT = DDCIINTB.DATA.NEG_INT + DDCIINTB.DATA.OD_INT + DDCIINTB.DATA.UOD_INT + DDCIINTB.DATA.AJS_NEG_BAL + DDCIINTB.DATA.AJS_OD_BAL + DDCIINTB.DATA.AJS_UOD_BAL;
        DDCOIBAL.NOT_INT_BAL = DDRCCY.NOT_INT_BAL;
        CEP.TRC(SCCGWA, "OUTPUT DATE:");
        CEP.TRC(SCCGWA, DDCSIBAL.AC_NO);
        CEP.TRC(SCCGWA, DDCSIBAL.CCY);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, DDCOIBAL.CR_INT);
        CEP.TRC(SCCGWA, DDRINTB.DEP_ACCU_INT);
        CEP.TRC(SCCGWA, DDRINTB.DEP_ADJ_INT);
        CEP.TRC(SCCGWA, DDCOIBAL.TODAY_TOTAL_DRAW_AMT);
        CEP.TRC(SCCGWA, WS_CURR_BAL);
        CEP.TRC(SCCGWA, WS_AVL_BAL);
        CEP.TRC(SCCGWA, WS_HOLD_BAL);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.DEP_INT);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.NEG_INT);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.OD_INT);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.UOD_INT);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.AJS_DEP_BAL);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.AJS_NEG_BAL);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.AJS_OD_BAL);
        CEP.TRC(SCCGWA, DDCIINTB.DATA.AJS_UOD_BAL);
    }
    public void S000_CALL_DDZIINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INTB-PROC", DDCIINTB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIINTB.RC);
        if ((DDCIINTB.RC.RC_CODE != 0) 
            && (!JIBS_tmp_str[0].equalsIgnoreCase(DDCMSG_ERROR_MSG.DD_INTB_REC_NOTFND))) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIINTB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_READ_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,LAST_FN_DATE";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DDZUINQV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-VSAC", DDCUINQV, true);
        if (DDCUINQV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUINQV.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
