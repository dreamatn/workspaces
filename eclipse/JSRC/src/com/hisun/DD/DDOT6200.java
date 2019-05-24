package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6200 {
    DBParm DDTADMN_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSADMN DDCSADMN = new DDCSADMN();
    CICQACAC CICQACAC = new CICQACAC();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DDRADMN DDRADMN = new DDRADMN();
    SCCGWA SCCGWA;
    DDB6200_AWA_6200 DDB6200_AWA_6200;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT6200 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6200_AWA_6200>");
        DDB6200_AWA_6200 = (DDB6200_AWA_6200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.FUNC);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.ADP_NO);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.ADP_TYPE);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.AC);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.AC_NM);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.CCY);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.CCY_TYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.STR_DATE);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.EXP_DATE);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_AMT);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_INTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_RFTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_RATE);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_RPCT);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_RVAR);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.OD_DAYS);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RCTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RFTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RATE);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RPCT);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RVAR);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RATYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_RTERM);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LFLG);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LCTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LFTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LRATE);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LRPCT);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LRVAR);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LRTYP);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.LN_LRTRM);
        CEP.TRC(SCCGWA, DDB6200_AWA_6200.FEE_CD);
        if (DDB6200_AWA_6200.FUNC == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT);
        }
        if (DDB6200_AWA_6200.FUNC != '1' 
            && DDB6200_AWA_6200.FUNC != '2' 
            && DDB6200_AWA_6200.FUNC != '3' 
            && DDB6200_AWA_6200.FUNC != '4' 
            && DDB6200_AWA_6200.FUNC != '5' 
            && DDB6200_AWA_6200.FUNC != '6') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_INVALID);
        }
        if (DDB6200_AWA_6200.FUNC == '1') {
            B110_CHECK_INPUT_ADD();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.FUNC == '2') {
            B120_CHECK_INPUT_MOD();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.FUNC == '3') {
            B130_CHECK_INPUT_STP();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.FUNC == '4') {
            B140_CHECK_INPUT_INQ();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.FUNC == '5') {
            B150_CHECK_INPUT_TEMP_STP();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.FUNC == '5') {
            B160_CHECK_INPUT_REUSE();
            if (pgmRtn) return;
        }
    }
    public void B110_CHECK_INPUT_ADD() throws IOException,SQLException,Exception {
        if (DDB6200_AWA_6200.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        } else {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDB6200_AWA_6200.AC;
            R000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CUS_AC_NOTFND);
            }
            if (DDRMST.AC_STS != 'N') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_STS_NOT_NORMAL);
            }
        }
        if (DDB6200_AWA_6200.ADP_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADP_TYPE_M_INP);
        }
        if (DDB6200_AWA_6200.STR_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDT_M_INPUT);
        } else {
            if (DDB6200_AWA_6200.STR_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDT_M_GTEQ_ACDT);
            }
        }
        if (DDB6200_AWA_6200.EXP_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_INPUT);
        } else {
            if (DDB6200_AWA_6200.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_GTEQ_ACDT);
            }
            if (DDB6200_AWA_6200.EXP_DATE <= DDB6200_AWA_6200.STR_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_GT_STRDT);
            }
        }
        if (DDB6200_AWA_6200.OD_AMT <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_AMT_M_GT_ZERO);
        }
        if (DDB6200_AWA_6200.OD_INTYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CAL_INT_METH_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.OD_RFTYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_RFTYP_M_INPUT);
        } else {
            if (DDB6200_AWA_6200.OD_RFTYP != '0' 
                && DDB6200_AWA_6200.OD_RFTYP != '1' 
                && DDB6200_AWA_6200.OD_RFTYP != '2' 
                && DDB6200_AWA_6200.OD_RFTYP != '3' 
                && DDB6200_AWA_6200.OD_RFTYP != '4') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_RFTYP_INVALID);
            }
        }
        if (DDB6200_AWA_6200.OD_RATE <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_RATE_M_GT_ZERO);
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '0' 
            && (DDB6200_AWA_6200.OD_RPCT != 0 
            || DDB6200_AWA_6200.OD_RVAR != 0)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_INP_ERR);
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '1') {
            if (DDB6200_AWA_6200.OD_RPCT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_CANT_INPUT);
            }
            if (DDB6200_AWA_6200.OD_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_M_INPUT);
            }
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '2') {
            if (DDB6200_AWA_6200.OD_RPCT == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_M_INPUT);
            }
            if (DDB6200_AWA_6200.OD_RVAR != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_CANT_INPUT);
            }
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '3' 
            || DDB6200_AWA_6200.OD_RFTYP == '4') {
            if (DDB6200_AWA_6200.OD_RPCT == 0 
                || DDB6200_AWA_6200.OD_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_BOTH_INPUT);
            }
        }
        if (DDRMST.CI_TYP == '3' 
            && DDB6200_AWA_6200.OD_DAYS == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IBAC_OD_DAYS_INP_ERR);
        }
        if (DDRMST.CI_TYP == '2' 
            && DDB6200_AWA_6200.OD_DAYS != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CMAC_OD_DAYS_INP_ERR);
        }
        if (DDB6200_AWA_6200.LN_RCTYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RCTYP_M_INP);
        } else {
            if (DDB6200_AWA_6200.LN_RCTYP != 'A' 
                && DDB6200_AWA_6200.LN_RCTYP != 'F' 
                && DDB6200_AWA_6200.LN_RCTYP != 'T') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RCTYP_INVALID);
            }
        }
        if (DDB6200_AWA_6200.LN_RCTYP == 'A') {
            if (DDB6200_AWA_6200.LN_RATYP.trim().length() == 0 
                || DDB6200_AWA_6200.LN_RTERM.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_RAT_TERM_BOTH_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDB6200_AWA_6200.LN_RFTYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RFTYP_M_INP);
        } else {
            if (DDB6200_AWA_6200.LN_RFTYP != '0' 
                && DDB6200_AWA_6200.LN_RFTYP != '1' 
                && DDB6200_AWA_6200.LN_RFTYP != '2' 
                && DDB6200_AWA_6200.LN_RFTYP != '3' 
                && DDB6200_AWA_6200.LN_RFTYP != '4') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RFTYP_INVALID);
            }
        }
        if (DDB6200_AWA_6200.LN_RATE <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RATE_M_GT_ZERO);
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '0' 
            && (DDB6200_AWA_6200.LN_RPCT != 0 
            || DDB6200_AWA_6200.LN_RVAR != 0)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_INP_ERR);
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '1') {
            if (DDB6200_AWA_6200.LN_RPCT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_CANT_INPUT);
            }
            if (DDB6200_AWA_6200.LN_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_M_INPUT);
            }
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '2') {
            if (DDB6200_AWA_6200.LN_RPCT == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LNPCT_M_INPUT);
            }
            if (DDB6200_AWA_6200.LN_RVAR != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_CANT_INPUT);
            }
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '3' 
            || DDB6200_AWA_6200.LN_RFTYP == '4') {
            if (DDB6200_AWA_6200.LN_RPCT == 0 
                || DDB6200_AWA_6200.LN_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_BOTH_INPUT);
            }
        }
        if ((DDB6200_AWA_6200.LN_RATYP.trim().length() > 0 
            && DDB6200_AWA_6200.LN_RTERM.trim().length() == 0) 
            || (DDB6200_AWA_6200.LN_RATYP.trim().length() == 0 
            && DDB6200_AWA_6200.LN_RTERM.trim().length() > 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LN_RATYP_TERM_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.LN_LFLG == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LN_L_USE_FLG_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.LN_LFLG == 'Y') {
            if (DDB6200_AWA_6200.LN_LCTYP != ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LCTYP_M_BE_SPACE);
            }
            if (DDB6200_AWA_6200.LN_LFTYP != ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LFTYP_M_BE_SPACE);
            }
            if (DDB6200_AWA_6200.LN_LRATE != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRATE_M_BE_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LRPCT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRPCT_M_BE_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LRVAR != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRVAR_M_BE_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LRTYP.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRTYP_M_BE_SPACE);
            }
            if (DDB6200_AWA_6200.LN_LRTRM.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRTRM_M_BE_SPACE);
            }
        }
        if (DDB6200_AWA_6200.LN_LFLG == 'N') {
            if (DDB6200_AWA_6200.LN_LCTYP == ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LCTYP_M_INPUT);
            }
            if (DDB6200_AWA_6200.LN_LFTYP == ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LFTYP_M_INPUT);
            } else {
                if (DDB6200_AWA_6200.LN_LFTYP != '0' 
                    && DDB6200_AWA_6200.LN_LFTYP != '1' 
                    && DDB6200_AWA_6200.LN_LFTYP != '2' 
                    && DDB6200_AWA_6200.LN_LFTYP != '3' 
                    && DDB6200_AWA_6200.LN_LFTYP != '4') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LFTYP_M_INVALID);
                }
            }
            if (DDB6200_AWA_6200.LN_LRATE <= 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRATE_M_GT_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '0' 
                && (DDB6200_AWA_6200.LN_LRPCT != 0 
                || DDB6200_AWA_6200.LN_LRVAR != 0)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_INP_ERR);
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '1') {
                if (DDB6200_AWA_6200.LN_LRPCT != 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_CANT_INPUT);
                }
                if (DDB6200_AWA_6200.LN_LRVAR == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_M_INPUT);
                }
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '2') {
                if (DDB6200_AWA_6200.LN_LRPCT == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRPCT_M_INPUT);
                }
                if (DDB6200_AWA_6200.LN_LRVAR != 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_CANT_INPUT);
                }
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '3' 
                || DDB6200_AWA_6200.LN_LFTYP == '4') {
                if (DDB6200_AWA_6200.LN_LRPCT == 0 
                    || DDB6200_AWA_6200.LN_LRVAR == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_BOTH_INPUT);
                }
            }
            if ((DDB6200_AWA_6200.LN_LRTYP.trim().length() > 0 
                && DDB6200_AWA_6200.LN_LRTRM.trim().length() == 0) 
                || (DDB6200_AWA_6200.LN_LRTYP.trim().length() == 0 
                && DDB6200_AWA_6200.LN_LRTRM.trim().length() > 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LN_LRTYP_LRTRM_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B120_CHECK_INPUT_MOD() throws IOException,SQLException,Exception {
        if (DDB6200_AWA_6200.ADP_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADPNO_M_INPUT);
        }
        if (DDB6200_AWA_6200.ADP_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADP_TYPE_M_INP);
        }
        if (DDB6200_AWA_6200.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDB6200_AWA_6200.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDB6200_AWA_6200.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB6200_AWA_6200.AC;
        CICQACAC.DATA.CCY_ACAC = DDB6200_AWA_6200.CCY;
        CICQACAC.DATA.CR_FLG = DDB6200_AWA_6200.CCY_TYP;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDB6200_AWA_6200.AC;
        R000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDB6200_AWA_6200.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDB6200_AWA_6200.ADP_TYPE;
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        R000_READ_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRADMN.ADP_STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
        if (DDRADMN.ADP_STS == 'F') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_ALR_F);
        }
        if (DDRADMN.ADP_STS == 'D') {
            if (DDB6200_AWA_6200.STR_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_ALR_D);
            }
            if (DDB6200_AWA_6200.STR_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDT_M_GTEQ_ACDT);
            }
        }
        if (DDRADMN.ADP_STS == 'O' 
            && DDB6200_AWA_6200.STR_DATE != DDRADMN.ADP_STRDATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDATE_NOTSAME);
        }
        if (DDB6200_AWA_6200.EXP_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_INPUT);
        } else {
            if (DDB6200_AWA_6200.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_GTEQ_ACDT);
            }
            if (DDB6200_AWA_6200.EXP_DATE <= DDB6200_AWA_6200.STR_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXPDT_M_GT_STRDT);
            }
        }
        if (DDB6200_AWA_6200.OD_AMT != DDRADMN.OD_AMT) {
            if (DDB6200_AWA_6200.OD_AMT <= 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_AMT_M_GT_ZERO);
            }
            if (DDB6200_AWA_6200.OD_AMT > DDRADMN.ADV_AMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ODAMT_CANT_GT_ADVAMT);
            }
        }
        if (DDB6200_AWA_6200.OD_INTYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CAL_INT_METH_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.OD_RATE <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_RATE_M_GT_ZERO);
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '0' 
            && (DDB6200_AWA_6200.OD_RPCT != 0 
            || DDB6200_AWA_6200.OD_RVAR != 0)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_INP_ERR);
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '1') {
            if (DDB6200_AWA_6200.OD_RPCT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_CANT_INPUT);
            }
            if (DDB6200_AWA_6200.OD_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_M_INPUT);
            }
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '2') {
            if (DDB6200_AWA_6200.OD_RPCT == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_M_INPUT);
            }
            if (DDB6200_AWA_6200.OD_RVAR != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_CANT_INPUT);
            }
        }
        if (DDB6200_AWA_6200.OD_RFTYP == '3' 
            || DDB6200_AWA_6200.OD_RFTYP == '4') {
            if (DDB6200_AWA_6200.OD_RPCT == 0 
                || DDB6200_AWA_6200.OD_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_BOTH_INPUT);
            }
        }
        if (DDRMST.CI_TYP == '3' 
            && DDB6200_AWA_6200.OD_DAYS == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IBAC_OD_DAYS_INP_ERR);
        }
        if (DDRMST.CI_TYP == '2' 
            && DDB6200_AWA_6200.OD_DAYS != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CMAC_OD_DAYS_INP_ERR);
        }
        if (DDB6200_AWA_6200.LN_RCTYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RCTYP_M_INP);
        } else {
            if (DDB6200_AWA_6200.LN_RCTYP != 'A' 
                && DDB6200_AWA_6200.LN_RCTYP != 'F' 
                && DDB6200_AWA_6200.LN_RCTYP != 'T') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RCTYP_INVALID);
            }
        }
        if (DDB6200_AWA_6200.LN_RFTYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RFTYP_M_INP);
        } else {
            if (DDB6200_AWA_6200.LN_RFTYP != '0' 
                && DDB6200_AWA_6200.LN_RFTYP != '1' 
                && DDB6200_AWA_6200.LN_RFTYP != '2' 
                && DDB6200_AWA_6200.LN_RFTYP != '3' 
                && DDB6200_AWA_6200.LN_RFTYP != '4') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RFTYP_INVALID);
            }
        }
        if (DDB6200_AWA_6200.LN_RATE <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LN_RATE_M_GT_ZERO);
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '0' 
            && (DDB6200_AWA_6200.LN_RPCT != 0 
            || DDB6200_AWA_6200.LN_RVAR != 0)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_INP_ERR);
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '1') {
            if (DDB6200_AWA_6200.LN_RPCT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_CANT_INPUT);
            }
            if (DDB6200_AWA_6200.LN_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_M_INPUT);
            }
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '2') {
            if (DDB6200_AWA_6200.LN_RPCT == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LNPCT_M_INPUT);
            }
            if (DDB6200_AWA_6200.LN_RVAR != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_CANT_INPUT);
            }
        }
        if (DDB6200_AWA_6200.LN_RFTYP == '3' 
            || DDB6200_AWA_6200.LN_RFTYP == '4') {
            if (DDB6200_AWA_6200.LN_RPCT == 0 
                || DDB6200_AWA_6200.LN_RVAR == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_BOTH_INPUT);
            }
        }
        if ((DDB6200_AWA_6200.LN_RATYP.trim().length() > 0 
            && DDB6200_AWA_6200.LN_RTERM.trim().length() == 0) 
            || (DDB6200_AWA_6200.LN_RATYP.trim().length() == 0 
            && DDB6200_AWA_6200.LN_RTERM.trim().length() > 0)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LN_RATYP_TERM_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.LN_LFLG == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LN_L_USE_FLG_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB6200_AWA_6200.LN_LFLG == 'Y') {
            if (DDB6200_AWA_6200.LN_LCTYP != ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LCTYP_M_BE_SPACE);
            }
            if (DDB6200_AWA_6200.LN_LFTYP != ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LFTYP_M_BE_SPACE);
            }
            if (DDB6200_AWA_6200.LN_LRATE != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRATE_M_BE_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LRPCT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRPCT_M_BE_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LRVAR != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRVAR_M_BE_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LRTYP.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRTYP_M_BE_SPACE);
            }
            if (DDB6200_AWA_6200.LN_LRTRM.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRTRM_M_BE_SPACE);
            }
        }
        if (DDB6200_AWA_6200.LN_LFLG == 'N') {
            if (DDB6200_AWA_6200.LN_LCTYP == ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LCTYP_M_INPUT);
            }
            if (DDB6200_AWA_6200.LN_LFTYP == ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LFTYP_M_INPUT);
            } else {
                if (DDB6200_AWA_6200.LN_LFTYP != '0' 
                    && DDB6200_AWA_6200.LN_LFTYP != '1' 
                    && DDB6200_AWA_6200.LN_LFTYP != '2' 
                    && DDB6200_AWA_6200.LN_LFTYP != '3' 
                    && DDB6200_AWA_6200.LN_LFTYP != '4') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LFTYP_M_INVALID);
                }
            }
            if (DDB6200_AWA_6200.LN_LRATE <= 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRATE_M_GT_ZERO);
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '0' 
                && (DDB6200_AWA_6200.LN_LRPCT != 0 
                || DDB6200_AWA_6200.LN_LRVAR != 0)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_INP_ERR);
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '1') {
                if (DDB6200_AWA_6200.LN_LRPCT != 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_CANT_INPUT);
                }
                if (DDB6200_AWA_6200.LN_LRVAR == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_M_INPUT);
                }
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '2') {
                if (DDB6200_AWA_6200.LN_LRPCT == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LRPCT_M_INPUT);
                }
                if (DDB6200_AWA_6200.LN_LRVAR != 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VAR_CANT_INPUT);
                }
            }
            if (DDB6200_AWA_6200.LN_LFTYP == '3' 
                || DDB6200_AWA_6200.LN_LFTYP == '4') {
                if (DDB6200_AWA_6200.LN_LRPCT == 0 
                    || DDB6200_AWA_6200.LN_LRVAR == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PCT_VAR_BOTH_INPUT);
                }
            }
            if ((DDB6200_AWA_6200.LN_LRTYP.trim().length() > 0 
                && DDB6200_AWA_6200.LN_LRTRM.trim().length() == 0) 
                || (DDB6200_AWA_6200.LN_LRTYP.trim().length() == 0 
                && DDB6200_AWA_6200.LN_LRTRM.trim().length() > 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LN_LRTYP_LRTRM_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B130_CHECK_INPUT_STP() throws IOException,SQLException,Exception {
        if (DDB6200_AWA_6200.ADP_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADPNO_M_INPUT);
        }
        if (DDB6200_AWA_6200.ADP_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADP_TYPE_M_INP);
        }
        if (DDB6200_AWA_6200.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDB6200_AWA_6200.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDB6200_AWA_6200.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB6200_AWA_6200.AC;
        CICQACAC.DATA.CCY_ACAC = DDB6200_AWA_6200.CCY;
        CICQACAC.DATA.CR_FLG = DDB6200_AWA_6200.CCY_TYP;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDB6200_AWA_6200.AC;
        R000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDB6200_AWA_6200.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDB6200_AWA_6200.ADP_TYPE;
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        R000_READ_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRADMN.ADP_STS);
        CEP.TRC(SCCGWA, DDRADMN.DAY_MAX_OD_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
        if (DDRADMN.ADP_STS == 'F') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_ALR_F);
        }
        if (DDRADMN.ADP_STS == 'O') {
            IBS.init(SCCGWA, DDRINTB);
            DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDRINTB.KEY.TYPE = 'O';
            R000_READ_DDTINTB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRINTB.DEP_TOT1);
            CEP.TRC(SCCGWA, DDRINTB.DEP_ACCU_INT);
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            R000_READ_DDTCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            if (DDRINTB.DEP_TOT1 != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_STOP_BCS_TOT);
            }
            if (DDRINTB.DEP_TOT1 == 0 
                && DDRINTB.DEP_ACCU_INT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_STOP_BCS_INT);
            }
            if (DDRINTB.DEP_TOT1 == 0 
                && DDRCCY.CURR_BAL < 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_STOP_BCS_BAL);
            }
            if (DDRINTB.DEP_TOT1 == 0 
                && DDRMST.CI_TYP == '1' 
                && DDRADMN.DAY_MAX_OD_AMT != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_STOP_BCS_AMT);
            }
        }
    }
    public void B140_CHECK_INPUT_INQ() throws IOException,SQLException,Exception {
        if (DDB6200_AWA_6200.ADP_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADPNO_M_INPUT);
        }
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDB6200_AWA_6200.ADP_NO;
        R100_READ_DDTADMN_BY_ADPNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
    }
    public void R100_READ_DDTADMN_BY_ADPNO() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "ADP_NO = :DDRADMN.KEY.ADP_NO";
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void B150_CHECK_INPUT_TEMP_STP() throws IOException,SQLException,Exception {
        B156_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B160_CHECK_INPUT_REUSE() throws IOException,SQLException,Exception {
        B156_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B156_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB6200_AWA_6200.ADP_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADPNO_M_INPUT);
        }
        if (DDB6200_AWA_6200.ADP_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADP_TYPE_M_INP);
        }
        if (DDB6200_AWA_6200.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDB6200_AWA_6200.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDB6200_AWA_6200.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB6200_AWA_6200.AC;
        CICQACAC.DATA.CCY_ACAC = DDB6200_AWA_6200.CCY;
        CICQACAC.DATA.CR_FLG = DDB6200_AWA_6200.CCY_TYP;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDB6200_AWA_6200.AC;
        R000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDB6200_AWA_6200.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDB6200_AWA_6200.ADP_TYPE;
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        R000_READ_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRADMN.ADP_STS);
        CEP.TRC(SCCGWA, DDRADMN.DAY_MAX_OD_AMT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
        if (DDRADMN.ADP_STS == 'F') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_ALR_F);
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSADMN);
        DDCSADMN.FUNC = DDB6200_AWA_6200.FUNC;
        DDCSADMN.ADP_NO = DDB6200_AWA_6200.ADP_NO;
        DDCSADMN.ADP_TYPE = DDB6200_AWA_6200.ADP_TYPE;
        DDCSADMN.AC = DDB6200_AWA_6200.AC;
        DDCSADMN.AC_NM = DDB6200_AWA_6200.AC_NM;
        DDCSADMN.CCY = DDB6200_AWA_6200.CCY;
        DDCSADMN.CCY_TYP = DDB6200_AWA_6200.CCY_TYP;
        DDCSADMN.STR_DATE = DDB6200_AWA_6200.STR_DATE;
        DDCSADMN.EXP_DATE = DDB6200_AWA_6200.EXP_DATE;
        DDCSADMN.OD_AMT = DDB6200_AWA_6200.OD_AMT;
        DDCSADMN.OD_INTYP = DDB6200_AWA_6200.OD_INTYP;
        DDCSADMN.OD_RFTYP = DDB6200_AWA_6200.OD_RFTYP;
        DDCSADMN.OD_RATE = DDB6200_AWA_6200.OD_RATE;
        DDCSADMN.OD_RPCT = DDB6200_AWA_6200.OD_RPCT;
        DDCSADMN.OD_RVAR = DDB6200_AWA_6200.OD_RVAR;
        DDCSADMN.OD_DAYS = DDB6200_AWA_6200.OD_DAYS;
        DDCSADMN.LN_RCTYP = DDB6200_AWA_6200.LN_RCTYP;
        DDCSADMN.LN_RFTYP = DDB6200_AWA_6200.LN_RFTYP;
        DDCSADMN.LN_RATE = DDB6200_AWA_6200.LN_RATE;
        DDCSADMN.LN_RPCT = DDB6200_AWA_6200.LN_RPCT;
        DDCSADMN.LN_RVAR = DDB6200_AWA_6200.LN_RVAR;
        DDCSADMN.LN_RTYP = DDB6200_AWA_6200.LN_RATYP;
        DDCSADMN.LN_RTERM = DDB6200_AWA_6200.LN_RTERM;
        DDCSADMN.LN_LFLG = DDB6200_AWA_6200.LN_LFLG;
        DDCSADMN.LN_LCTYP = DDB6200_AWA_6200.LN_LCTYP;
        DDCSADMN.LN_LFTYP = DDB6200_AWA_6200.LN_LFTYP;
        DDCSADMN.LN_LRATE = DDB6200_AWA_6200.LN_LRATE;
        DDCSADMN.LN_LRPCT = DDB6200_AWA_6200.LN_LRPCT;
        DDCSADMN.LN_LRVAR = DDB6200_AWA_6200.LN_LRVAR;
        DDCSADMN.LN_LRTYP = DDB6200_AWA_6200.LN_LRTYP;
        DDCSADMN.LN_LRTRM = DDB6200_AWA_6200.LN_LRTRM;
        DDCSADMN.FEE_CD = DDB6200_AWA_6200.FEE_CD;
        CEP.TRC(SCCGWA, DDCSADMN.FUNC);
        CEP.TRC(SCCGWA, DDCSADMN.ADP_NO);
        CEP.TRC(SCCGWA, DDCSADMN.ADP_TYPE);
        CEP.TRC(SCCGWA, DDCSADMN.AC);
        CEP.TRC(SCCGWA, DDCSADMN.AC_NM);
        CEP.TRC(SCCGWA, DDCSADMN.CCY);
        CEP.TRC(SCCGWA, DDCSADMN.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSADMN.STR_DATE);
        CEP.TRC(SCCGWA, DDCSADMN.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSADMN.OD_AMT);
        CEP.TRC(SCCGWA, DDCSADMN.OD_INTYP);
        CEP.TRC(SCCGWA, DDCSADMN.OD_RFTYP);
        CEP.TRC(SCCGWA, DDCSADMN.OD_RATE);
        CEP.TRC(SCCGWA, DDCSADMN.OD_RPCT);
        CEP.TRC(SCCGWA, DDCSADMN.OD_RVAR);
        CEP.TRC(SCCGWA, DDCSADMN.OD_DAYS);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RCTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RFTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RATE);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RPCT);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RVAR);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RTERM);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LFLG);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LCTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LFTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRATE);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRPCT);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRVAR);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRTRM);
        CEP.TRC(SCCGWA, DDCSADMN.FEE_CD);
        S000_CALL_DDZSADMN();
        if (pgmRtn) return;
    }
    public void R000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void R000_READ_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void R000_READ_DDTINTB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRINTB.KEY.AC);
        CEP.TRC(SCCGWA, DDRINTB.KEY.TYPE);
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void R000_READ_DDTADMN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_NO);
        CEP.TRC(SCCGWA, DDRADMN.KEY.AC);
        CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_TYPE);
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.READ(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_DDZSADMN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MOD-ADMN", DDCSADMN);
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
