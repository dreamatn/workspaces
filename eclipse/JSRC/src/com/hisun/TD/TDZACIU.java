package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZACIU {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm TDTBVT_BR = new brParm();
    String JIBS_NumStr;
    DBParm TDTIREV_RD;
    DBParm TDTINST_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTMST_RD;
    DBParm TDTAINT_RD;
    DBParm TDTBVT_RD;
    DBParm TDTCDI_RD;
    boolean pgmRtn = false;
    String K_UPD_ACIU_FMT = "TD512";
    String K_DD_ACCO = "DD";
    String K_DC_ACCO = "DC";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_STL_MTH = ' ';
    TDZACIU_WS_TERM_DESC WS_TERM_DESC = new TDZACIU_WS_TERM_DESC();
    TDZACIU_WS_TABLES_INFO WS_TABLES_INFO = new TDZACIU_WS_TABLES_INFO();
    short WS_CCY_TIME = 0;
    String WS_AC = " ";
    String CI_NO_TD = " ";
    String CI_NO_DD = " ";
    TDZACIU_CP_PROD_CD CP_PROD_CD = new TDZACIU_CP_PROD_CD();
    int WS_SEAL_NO = 0;
    double WS_PRV_RAT_O = 0;
    double WS_OVE_RAT_O = 0;
    double WS_COM_RAT_O = 0;
    double WS_DUE_RAT_O = 0;
    short WS_X = 0;
    short WS_C = 0;
    String WS_INSTR_MTH_DESC = " ";
    TDZACIU_REDEFINES39 REDEFINES39 = new TDZACIU_REDEFINES39();
    char WS_CI_TYP = ' ';
    String WS_TD_CNM = " ";
    String WS_DD_AC = " ";
    char WS_BVT_DB_FLG = ' ';
    char WS_CCYC_FLG = ' ';
    String WS_TERM = " ";
    TDZACIU_REDEFINES57 REDEFINES57 = new TDZACIU_REDEFINES57();
    TDZACIU_WS_RC_MSG WS_RC_MSG = new TDZACIU_WS_RC_MSG();
    short WS_TIMES = 0;
    short WS_TIMESS = 0;
    char WS_INSTR_FLG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    char WS_STS_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRCDI TDRCDI = new TDRCDI();
    TDCOACIU TDCOACIU = new TDCOACIU();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDRINST TDRINST = new TDRINST();
    DDRMST DDRMST = new DDRMST();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICACCU CICACCU = new CICACCU();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDRAINT TDRAINT = new TDRAINT();
    SCCHMPW SCCHMPW = new SCCHMPW();
    CICMACR CICMACR = new CICMACR();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    TDRCMST TDRCMST = new TDRCMST();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDRIREV TDRIREV = new TDRIREV();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCACIU TDCACIU;
    public void MP(SCCGWA SCCGWA, TDCACIU TDCACIU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCACIU = TDCACIU;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, TDCACIU.PRV_RAT_N);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_N);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZACIU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, TDCOACIU);
        IBS.init(SCCGWA, TDRBVT);
        CEP.TRC(SCCGWA, TDCACIU.ERLY_TYP_O);
        CEP.TRC(SCCGWA, TDCACIU.ERLY_TYP_N);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACIU.OTHE_CHG);
        if (TDCACIU.OTHE_CHG != 'O') {
            B100_CHK_INPUT_PROC();
            if (pgmRtn) return;
            B200_UPDATE_AC_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCPRDP.EXP_PRM.RES_TYP);
            CEP.TRC(SCCGWA, TDCPRDP.EXP_PRM.ERLY_TYP);
            CEP.TRC(SCCGWA, TDCPRDP.EXP_PRM.LATE_TYP);
            if ((TDCACIU.DUE_RAT_N != TDCACIU.DUE_RAT_O 
                && TDCPRDP.EXP_PRM.RES_TYP == '5') 
                || (TDCACIU.PRV_RAT_N != TDCACIU.PRV_RAT_O 
                && (TDCACIU.ERLY_TYP_N == '5' 
                || (TDCACIU.ERLY_TYP_O == '5' 
                && TDCACIU.ERLY_TYP_N != '5'))) 
                || (TDCACIU.OVE_RAT_N != TDCACIU.OVE_RAT_O 
                && TDCPRDP.EXP_PRM.LATE_TYP == '5')) {
                B400_UPD_TXY_AINT();
                if (pgmRtn) return;
            }
            B300_FMT_OUTPUT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDCACIU.ACO_AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "TSTJHT");
            TDRSMST.PROD_CD = TDCACIU.PROD_CD;
            TDRSMST.AC_TYP = TDCACIU.AC_TYP_N;
            TDRSMST.MON_TYP = TDCACIU.MON_TYP;
            TDRSMST.FC_NO = TDCACIU.FC_NO_N;
            TDRSMST.FC_CD = TDCACIU.FC_CD_N;
            T000_UPDATE_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_O);
        CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_N);
        CEP.TRC(SCCGWA, TDCACIU.TERM_O);
        CEP.TRC(SCCGWA, TDCACIU.TERM_N);
        CEP.TRC(SCCGWA, TDCACIU.NUM_O);
        CEP.TRC(SCCGWA, TDCACIU.NUM_N);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_O);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_N);
        CEP.TRC(SCCGWA, TDCACIU.AUTO_FLG_O);
        CEP.TRC(SCCGWA, TDCACIU.AUTO_FLG_N);
        CEP.TRC(SCCGWA, TDCACIU.PI_AC_O);
        CEP.TRC(SCCGWA, TDCACIU.PI_AC_N);
        CEP.TRC(SCCGWA, TDCACIU.INT_BR_O);
        CEP.TRC(SCCGWA, TDCACIU.INT_BR_N);
        CEP.TRC(SCCGWA, TDCACIU.INT_NM_O);
        CEP.TRC(SCCGWA, TDCACIU.INT_NM_N);
        CEP.TRC(SCCGWA, TDCACIU.DUE_RAT_O);
        CEP.TRC(SCCGWA, TDCACIU.DUE_RAT_N);
        CEP.TRC(SCCGWA, TDCACIU.PRV_RAT_O);
        CEP.TRC(SCCGWA, TDCACIU.PRV_RAT_N);
        CEP.TRC(SCCGWA, TDCACIU.OVE_RAT_O);
        CEP.TRC(SCCGWA, TDCACIU.OVE_RAT_N);
        CEP.TRC(SCCGWA, TDCACIU.AC_TYP_O);
        CEP.TRC(SCCGWA, TDCACIU.AC_TYP_N);
        CEP.TRC(SCCGWA, TDCACIU.OIC_NO_O);
        CEP.TRC(SCCGWA, TDCACIU.OIC_NO_N);
        CEP.TRC(SCCGWA, TDCACIU.RES_CD_O);
        CEP.TRC(SCCGWA, TDCACIU.RES_CD_N);
        CEP.TRC(SCCGWA, TDCACIU.SUB_NO_O);
        CEP.TRC(SCCGWA, TDCACIU.SUB_NO_N);
        CEP.TRC(SCCGWA, TDCACIU.DDT);
        CEP.TRC(SCCGWA, TDCACIU.MAIN_AC);
        CEP.TRC(SCCGWA, TDCACIU.AC_TYP_O);
        CEP.TRC(SCCGWA, TDCACIU.AC_TYP_N);
        CEP.TRC(SCCGWA, TDCACIU.OIC_NO_O);
        CEP.TRC(SCCGWA, TDCACIU.OIC_NO_N);
        CEP.TRC(SCCGWA, TDCACIU.RES_CD_O);
        CEP.TRC(SCCGWA, TDCACIU.RES_CD_N);
        CEP.TRC(SCCGWA, TDCACIU.SUB_NO_O);
        CEP.TRC(SCCGWA, TDCACIU.SUB_NO_N);
        CEP.TRC(SCCGWA, TDCACIU.PROD_CD);
        CEP.TRC(SCCGWA, TDCACIU.AC_TYP);
        CEP.TRC(SCCGWA, TDCACIU.MON_TYP);
        CEP.TRC(SCCGWA, TDCACIU.OTHE_CHG);
        CEP.TRC(SCCGWA, TDCACIU.ACO_AC);
        CEP.TRC(SCCGWA, TDCACIU.ERLY_TYP_O);
        CEP.TRC(SCCGWA, TDCACIU.ERLY_TYP_N);
        if (TDCACIU.BV_TYP != '4') {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCACIU.MAIN_AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCACIU.AC_SEQ);
        CEP.TRC(SCCGWA, TDCACIU.BV_NO);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCACIU.MAIN_AC;
        CICQACAC.DATA.AGR_SEQ = TDCACIU.AC_SEQ;
        CICQACAC.DATA.BV_NO = TDCACIU.BV_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = TDRSMST.OWNER_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        if (TDRCMST.CI_TYP == '2' 
            && BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TRADE_BR_NOT_SAME);
        }
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        if (TDRCMST.BV_TYP != ' ') {
            TDCACIU.BV_TYP = TDRCMST.BV_TYP;
        }
        if (TDRSMST.BV_TYP != ' ') {
            TDCACIU.BV_TYP = TDRSMST.BV_TYP;
        }
        if ((TDCACIU.ERLY_TYP_O == '1' 
            || TDCACIU.ERLY_TYP_O == '5') 
            && (TDCACIU.ERLY_TYP_N == '2' 
            || TDCACIU.ERLY_TYP_N == '3' 
            || TDCACIU.ERLY_TYP_N == '4')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_ERLY_TYP);
        }
        CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
        CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
        CEP.TRC(SCCGWA, TDCACIU.BV_TYP);
        if (TDCACIU.BV_TYP == '1') {
            B110_GET_BV_TYP_UNT();
            if (pgmRtn) return;
        }
        if (TDCACIU.INSTR_MTH_O == TDCACIU.INSTR_MTH_N 
            && TDCACIU.TERM_O.equalsIgnoreCase(TDCACIU.TERM_N) 
            && TDCACIU.NUM_O == TDCACIU.NUM_N 
            && TDCACIU.PAY_AC_O.equalsIgnoreCase(TDCACIU.PAY_AC_N) 
            && TDCACIU.AUTO_FLG_O == TDCACIU.AUTO_FLG_N 
            && TDCACIU.PI_AC_O.equalsIgnoreCase(TDCACIU.PI_AC_N) 
            && TDCACIU.INT_BR_O == TDCACIU.INT_BR_N 
            && TDCACIU.INT_NM_O.equalsIgnoreCase(TDCACIU.INT_NM_N) 
            && TDCACIU.DUE_RAT_O == TDCACIU.DUE_RAT_N 
            && TDCACIU.PRV_RAT_O == TDCACIU.PRV_RAT_N 
            && TDCACIU.OVE_RAT_O == TDCACIU.OVE_RAT_N 
            && TDCACIU.AC_TYP_O == TDCACIU.AC_TYP_N 
            && TDCACIU.OIC_NO_O.equalsIgnoreCase(TDCACIU.OIC_NO_N) 
            && TDCACIU.RES_CD_O.equalsIgnoreCase(TDCACIU.RES_CD_N) 
            && TDCACIU.SUB_NO_O.equalsIgnoreCase(TDCACIU.SUB_NO_N) 
            && TDCACIU.DDT == TDRSMST.EXP_DATE 
            && TDCACIU.ERLY_TYP_O == TDCACIU.ERLY_TYP_N) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NOT_CHNL);
        }
        if (TDCACIU.INSTR_MTH_N == ' ' 
            && TDCACIU.PAY_AC_N.trim().length() > 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_STL_ISR_NEED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACIU.INSTR_MTH_N == ' ') {
            TDCACIU.INSTR_MTH_N = TDCACIU.INSTR_MTH_O;
        }
        if ((TDCACIU.INSTR_MTH_N == '1' 
            || TDCACIU.INSTR_MTH_N == '6') 
            && TDCACIU.PAY_AC_N.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_STL_AC_NEED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((TDCACIU.INSTR_MTH_N != '1' 
            && TDCACIU.INSTR_MTH_N != '6') 
            && TDCACIU.PAY_AC_N.trim().length() > 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_N_STL_N_IPT_DDAC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_GET_BV_TYP_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        if (TDRSMST.BV_TYP != ' ' 
            && TDRCMST.BV_TYP == '0') {
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
        } else {
            TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
        }
        T000_READ_TDTBVT();
        if (pgmRtn) return;
    }
    public void B200_UPDATE_AC_INFO() throws IOException,SQLException,Exception {
        if (TDCACIU.MAIN_AC.trim().length() > 0 
            && TDCACIU.AC_SEQ == 0 
            && TDCACIU.BV_TYP != '7') {
            CEP.TRC(SCCGWA, TDCACIU.BV_TYP);
            if (TDCACIU.BV_TYP != '4' 
                && TDCACIU.BV_TYP != '5' 
                && TDCACIU.BV_TYP != '8' 
                && TDCACIU.BV_TYP != '9') {
                B240_READ_UPDATE_TDTBVT();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDCACIU.AC_SEQ);
        CEP.TRC(SCCGWA, TDCACIU.MAIN_AC);
        WS_AC = TDRSMST.KEY.ACO_AC;
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_AC);
        B233_CHK_CI_INF();
        if (pgmRtn) return;
        B230_READ_UPDATE_TDTSMST();
        if (pgmRtn) return;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
        B200_CHECK_AC_INPUT();
        if (pgmRtn) return;
        if (TDCACIU.BV_TYP == '1' 
            || TDCACIU.BV_TYP == '3' 
            || TDCACIU.BV_TYP == '6' 
            || TDCACIU.BV_TYP == '7') {
            B240_READ_UPDATE_TDTBVT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
        CEP.TRC(SCCGWA, TDCACIU.NUM_N);
        CEP.TRC(SCCGWA, TDCACIU.NUM_O);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_N);
        CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_N);
        CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_O);
        CEP.TRC(SCCGWA, TDCACIU.TERM_N);
        CEP.TRC(SCCGWA, TDCACIU.TERM_O);
        if (TDCACIU.PAY_AC_N.trim().length() > 0 
            || TDCACIU.INSTR_MTH_N != TDCACIU.INSTR_MTH_O 
            || !TDCACIU.TERM_N.equalsIgnoreCase(TDCACIU.TERM_O) 
            || TDCACIU.NUM_N != TDCACIU.NUM_O) {
            B260_READ_UPDATE_TDTINST();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if ((TDCACIU.PI_AC_N.trim().length() > 0 
            || TDCACIU.AUTO_FLG_N != ' ') 
            && (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1"))) {
            B250_READ_UPDATE_TDTCDI();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_AC_INPUT() throws IOException,SQLException,Exception {
    }
    public void B400_UPD_TXY_AINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRAINT);
        TDRAINT.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READU_TDTAINT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, TDRAINT);
            TDRAINT.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRAINT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRAINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRAINT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRAINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_TDTAINT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRAINT);
            TDRAINT.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READU_TDTAINT();
            if (pgmRtn) return;
        }
        WS_PRV_RAT_O = TDRAINT.PRV_RAT;
        WS_OVE_RAT_O = TDRAINT.OVE_RAT;
        WS_COM_RAT_O = TDRAINT.COM_RAT;
        WS_DUE_RAT_O = TDRAINT.DUE_RAT;
        CEP.TRC(SCCGWA, TDCACIU.PRV_RAT_N);
        CEP.TRC(SCCGWA, TDCACIU.OVE_RAT_N);
        CEP.TRC(SCCGWA, TDCACIU.DUE_RAT_N);
        CEP.TRC(SCCGWA, TDRAINT.PRV_RAT);
        CEP.TRC(SCCGWA, TDRAINT.OVE_RAT);
        CEP.TRC(SCCGWA, TDRAINT.DUE_RAT);
        if (TDRAINT.PRV_RAT != TDCACIU.PRV_RAT_N 
            || TDRAINT.OVE_RAT != TDCACIU.OVE_RAT_N 
            || TDRAINT.DUE_RAT != TDCACIU.DUE_RAT_N) {
            if ((TDCACIU.PRV_RAT_N != TDCACIU.PRV_RAT_O 
                && TDCPRDP.EXP_PRM.ERLY_TYP == '5')) {
                TDRAINT.PRV_RAT = TDCACIU.PRV_RAT_N;
            }
            if ((TDCACIU.OVE_RAT_N != TDCACIU.OVE_RAT_O 
                && TDCPRDP.EXP_PRM.LATE_TYP == '5')) {
                TDRAINT.OVE_RAT = TDCACIU.OVE_RAT_N;
            }
            if ((TDCACIU.DUE_RAT_N != TDCACIU.DUE_RAT_O 
                && TDCPRDP.EXP_PRM.RES_TYP == '5')) {
                TDRAINT.DUE_RAT = TDCACIU.DUE_RAT_N;
            }
            T000_UPDATE_TDTAINT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDRAINT.KEY.ACO_AC;
            T000_READ_UPDATE_TDTSMST();
            if (pgmRtn) return;
            if (TDCACIU.PRV_RAT_N != TDCACIU.PRV_RAT_O 
                && TDCPRDP.EXP_PRM.ERLY_TYP == '5') {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 36 - 1) + "1" + TDRSMST.STSW.substring(36 + 1 - 1);
            }
            if (TDCACIU.OVE_RAT_N != TDCACIU.OVE_RAT_O 
                && TDCPRDP.EXP_PRM.LATE_TYP == '5') {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 37 - 1) + "1" + TDRSMST.STSW.substring(37 + 1 - 1);
            }
            if (TDCACIU.DUE_RAT_N != TDCACIU.DUE_RAT_O 
                && TDCPRDP.EXP_PRM.RES_TYP == '5') {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 38 - 1) + "1" + TDRSMST.STSW.substring(38 + 1 - 1);
            }
            T000_UPDATE_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B210_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        S000_CALL_DCCIMSTR();
        if (pgmRtn) return;
        if (DCCIMSTR.RC.RC_CODE == 0) {
            WS_AC = TDCACIU.MAIN_AC;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCIMSTR.DATA.AC_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B220_CALL_DCCUQSAC() throws IOException,SQLException,Exception {
        S000_CALL_DCCUQSAC();
        if (pgmRtn) return;
        if (TDCACIU.BV_TYP == '7') {
            WS_AC = DCCUQSAC.OUTP_DATA.SUB_AC;
        } else {
            WS_AC = DCCUQSAC.INP_DATA.AC;
        }
        CEP.TRC(SCCGWA, DCCUQSAC.OUTP_DATA.SUB_AC);
    }
    public void B230_READ_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACIU.TERM_N);
        if (TDRSMST.KEY.ACO_AC.trim().length() > 0) {
            if (TDRSMST.ACO_STS == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.ACO_STS == '2') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_TABLES_INFO.WS_SMST_AC = TDRSMST.AC_NO;
            WS_TABLES_INFO.WS_SMST_BV_TYP = TDCACIU.BV_TYP;
            WS_TABLES_INFO.WS_SMST_INSTR_MTH = TDRSMST.INSTR_MTH;
            TDCACIU.INSTR_MTH_O = TDRSMST.INSTR_MTH;
            CEP.TRC(SCCGWA, WS_TABLES_INFO.WS_SMST_INSTR_MTH);
            CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_O);
            CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_N);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            TDRSMST.AC_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, TDRSMST.OWNER_BK);
            if (TDRCMST.CROS_DR_FLG == '0') {
                if (TDRSMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    IBS.init(SCCGWA, BPCPRGST);
                    BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                    BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPCPRGST.BR2 = TDRSMST.OWNER_BR;
                    CEP.TRC(SCCGWA, BPCPRGST.BR1);
                    CEP.TRC(SCCGWA, BPCPRGST.BR2);
                    S000_CALL_BPZPRGST();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPRGST.FLAG);
                    CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
                    CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
                    if (BPCPRGST.BRANCH_FLG == 'Y') {
                    } else {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_CROS_DR_FLG);
                    }
                }
            } else if (TDRCMST.CROS_DR_FLG == '1') {
            } else if (TDRCMST.CROS_DR_FLG == '2') {
                if (TDRSMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_CROS_DR_FLG);
                }
            }
            CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
            if (TDRSMST.EXP_DATE != 0 
                && TDRSMST.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                if (TDCACIU.INSTR_MTH_O != TDCACIU.INSTR_MTH_N) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_EXP;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "2222222222222");
            CEP.TRC(SCCGWA, WS_TABLES_INFO.WS_SMST_INSTR_MTH);
            CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_N);
            B231_GET_PRD_INF();
            if (pgmRtn) return;
            B232_GET_PRD_PARM();
            if (pgmRtn) return;
            WS_INSTR_FLG = ' ';
            WS_TIMES = 0;
            if (TDCACIU.INSTR_MTH_N == ' ') WS_TIMESS = 0;
            else WS_TIMESS = Short.parseShort(""+TDCACIU.INSTR_MTH_N);
            if (TDCACIU.INSTR_MTH_N == '3' 
                || TDCACIU.INSTR_MTH_N == '4' 
                || TDCACIU.INSTR_MTH_N == '5' 
                || TDCACIU.INSTR_MTH_N == '6') {
                CEP.TRC(SCCGWA, TDCPRDP.EXP_PRM.INR_MTH);
                while (WS_TIMES <= 19 
                    && WS_INSTR_FLG != 'Y') {
                    WS_TIMES += 1;
                    if (TDCPRDP.EXP_PRM.INR_MTH == null) TDCPRDP.EXP_PRM.INR_MTH = "";
                    JIBS_tmp_int = TDCPRDP.EXP_PRM.INR_MTH.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.EXP_PRM.INR_MTH += " ";
                    if (TDCPRDP.EXP_PRM.INR_MTH.substring(WS_TIMES - 1, WS_TIMES + 1 - 1).equalsIgnoreCase("Y")) {
                        if (WS_TIMES - 1 == WS_TIMESS) {
                            WS_INSTR_FLG = 'Y';
                        } else {
                            WS_INSTR_FLG = 'N';
                        }
                    } else {
                        WS_INSTR_FLG = 'N';
                    }
                }
                if (WS_INSTR_FLG == 'N' 
                    && TDCACIU.INSTR_MTH_N != ' ') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_N_SUP_INSTR);
                }
            }
            R000_TERM_CHAKE();
            if (pgmRtn) return;
            if (WS_TABLES_INFO.WS_SMST_INSTR_MTH != TDCACIU.INSTR_MTH_N 
                && TDCACIU.INSTR_MTH_N != ' ') {
                TDRSMST.INSTR_MTH = TDCACIU.INSTR_MTH_N;
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            }
            if ((TDCACIU.SUB_NO_N.trim().length() > 0 
                && TDCACIU.RES_CD_N.trim().length() > 0)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_RES_SUB_ERR);
            }
            TDRSMST.OIC_NO = TDCACIU.OIC_NO_N;
            TDRSMST.SUB_DP = TDCACIU.SUB_NO_N;
            TDRSMST.RES_CD = TDCACIU.RES_CD_N;
            DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
                && TDCACIU.INSTR_MTH_O != TDCACIU.INSTR_MTH_N) {
                if (TDCACIU.INSTR_MTH_N == '0') {
                    TDRSMST.EXP_DATE = 0;
                }
                if (TDCACIU.INSTR_MTH_O == '0' 
                    && TDCACIU.INSTR_MTH_N != '0') {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = TDRSMST.VAL_DATE;
                    WS_TERM = TDRSMST.TERM;
                    IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES57);
                    SCCCLDT.DAYS = REDEFINES57.WS_TERM_MTHS;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    TDRSMST.EXP_DATE = SCCCLDT.DATE2;
                }
            }
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.TERM);
            CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("032")) 
                && TDRSMST.TERM.equalsIgnoreCase("S000")) {
                if (TDCACIU.DDT < TDRSMST.EXP_DATE) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DDT_I_ERR);
                }
                R000_UPDATE_TDTIREV();
                if (pgmRtn) return;
                TDRSMST.EXP_DATE = TDCACIU.DDT;
            }
            CEP.TRC(SCCGWA, "TSTJHT11");
            CEP.TRC(SCCGWA, TDCACIU.ERLY_TYP_O);
            CEP.TRC(SCCGWA, TDCACIU.ERLY_TYP_N);
            if (TDCACIU.ERLY_TYP_O != TDCACIU.ERLY_TYP_N) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                JIBS_tmp_str[0] = "" + TDCACIU.ERLY_TYP_N;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 34 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(34 + 1 - 1);
                if (TDCACIU.ERLY_TYP_N != '5') {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 36 - 1) + "0" + TDRSMST.STSW.substring(36 + 1 - 1);
                } else {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 36 - 1) + "1" + TDRSMST.STSW.substring(36 + 1 - 1);
                }
            }
            if (TDCACIU.AC_TYP_N != TDCACIU.AC_TYP_O) {
                TDRSMST.AC_TYP = TDCACIU.AC_TYP_N;
            }
            T000_UPDATE_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B231_GET_PRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
    }
    public void B232_GET_PRD_PARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        if (pgmRtn) return;
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_CCY_TIME = 0;
        while (WS_CCYC_FLG != 'Y' 
            && WS_CCY_TIME < 24) {
            WS_CCY_TIME += 1;
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].MIN_CCYC.equalsIgnoreCase(TDRSMST.CCY)) {
                WS_CCYC_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_CCY_TIME);
    }
    public void B261_CHECK_INSTR() throws IOException,SQLException,Exception {
        WS_C = 1;
        WS_X = 0;
        while (WS_C <= 7) {
            if (TDCPRDP.EXP_PRM.INR_MTH == null) TDCPRDP.EXP_PRM.INR_MTH = "";
            JIBS_tmp_int = TDCPRDP.EXP_PRM.INR_MTH.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.EXP_PRM.INR_MTH += " ";
            if (TDCPRDP.EXP_PRM.INR_MTH.substring(WS_C - 1, WS_C + 1 - 1).equalsIgnoreCase("Y")) {
                if (WS_INSTR_MTH_DESC == null) WS_INSTR_MTH_DESC = "";
                JIBS_tmp_int = WS_INSTR_MTH_DESC.length();
                for (int i=0;i<7-JIBS_tmp_int;i++) WS_INSTR_MTH_DESC += " ";
                JIBS_tmp_str[0] = "" + WS_X;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_INSTR_MTH_DESC = WS_INSTR_MTH_DESC.substring(0, WS_C - 1) + JIBS_tmp_str[0] + WS_INSTR_MTH_DESC.substring(WS_C + 1 - 1);
                IBS.CPY2CLS(SCCGWA, WS_INSTR_MTH_DESC, REDEFINES39);
            }
            WS_X = (short) (WS_X + 1);
            WS_C = (short) (WS_C + 1);
        }
        CEP.TRC(SCCGWA, REDEFINES39.WS_PENDING_INDICA);
        CEP.TRC(SCCGWA, REDEFINES39.WS_TURN_DEMAND_DEP);
        CEP.TRC(SCCGWA, REDEFINES39.WS_TURN_TIME_DEP);
        CEP.TRC(SCCGWA, REDEFINES39.WS_PRIN_INT_ROLL);
        CEP.TRC(SCCGWA, REDEFINES39.WS_ADD_PRIN_ROLL);
        CEP.TRC(SCCGWA, REDEFINES39.WS_SUBTR_PRIN_ROLL);
        CEP.TRC(SCCGWA, REDEFINES39.WS_PRIN_ROLL);
        if (TDCACIU.INSTR_MTH_N != REDEFINES39.WS_PENDING_INDICA 
            && TDCACIU.INSTR_MTH_N != REDEFINES39.WS_TURN_DEMAND_DEP 
            && TDCACIU.INSTR_MTH_N != REDEFINES39.WS_TURN_TIME_DEP 
            && TDCACIU.INSTR_MTH_N != REDEFINES39.WS_PRIN_INT_ROLL 
            && TDCACIU.INSTR_MTH_N != REDEFINES39.WS_ADD_PRIN_ROLL 
            && TDCACIU.INSTR_MTH_N != REDEFINES39.WS_SUBTR_PRIN_ROLL 
            && TDCACIU.INSTR_MTH_N != REDEFINES39.WS_PRIN_ROLL) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INSTR_MTH_NOT_ALLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCPRDP.EXP_PRM.MDF_FLG == 'N' 
            && TDCACIU.INSTR_MTH_O != TDCACIU.INSTR_MTH_N) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NO_ALLOW_UPD_INSTR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B233_CHK_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCACIU.MAIN_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_TYP = CICACCU.DATA.CI_TYP;
        WS_TD_CNM = CICACCU.DATA.CI_CNM;
        CEP.TRC(SCCGWA, "OUTPUTTEST");
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        CI_NO_TD = CICACCU.DATA.CI_NO;
    }
    public void B235_GET_CHK_DDAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = WS_DD_AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.CUS_TYP);
        if ((WS_CI_TYP == '2' 
            || WS_CI_TYP == '3')) {
            if (DDCSCINM.OUTPUT_DATA.CUS_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((WS_CI_TYP == '1')) {
            if (DDCSCINM.OUTPUT_DATA.CUS_TYP == '2' 
                || DDCSCINM.OUTPUT_DATA.CUS_TYP == '3') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYPE_M_ST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, CI_NO_TD);
        if (!CI_NO_TD.equalsIgnoreCase(DDCSCINM.OUTPUT_DATA.CI_NO)) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ABC");
    }
    public void B240_READ_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        B110_GET_BV_TYP_UNT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, TDCACIU.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (!TDCACIU.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_HAS_NOR_LOSS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_PLEDGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_UPDATE_TDTIREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.END_DATE = TDRSMST.EXP_DATE;
        T000_READUP_TDTIREV();
        if (pgmRtn) return;
        TDRIREV.END_DATE = TDCACIU.DDT;
        T000_REWRITE_TDTIREV();
        if (pgmRtn) return;
    }
    public void R000_TERM_CHAKE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM);
        CEP.TRC(SCCGWA, "111");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(0, 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_ONE_DAY = "D001";
        }
        CEP.TRC(SCCGWA, "222");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_SEVEN_DAY = "D007";
        }
        CEP.TRC(SCCGWA, "333");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_ONE_MONTH = "M001";
        }
        CEP.TRC(SCCGWA, "444");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_THREE_MONTH = "M003";
        }
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_SIX_MONTH = "M006";
        }
        CEP.TRC(SCCGWA, "555");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_ONE_YEAR = "Y001";
        }
        CEP.TRC(SCCGWA, "666");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_TWO_YEAR = "Y002";
        }
        CEP.TRC(SCCGWA, "777");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_THREE_YEAR = "Y003";
        }
        CEP.TRC(SCCGWA, "888");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_FIVE_YEAR = "Y005";
        }
        CEP.TRC(SCCGWA, "999");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_SIX_YEAR = "Y006";
        }
        CEP.TRC(SCCGWA, "000");
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM == null) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM = "";
        JIBS_tmp_int = TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM += " ";
        if (TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
            WS_TERM_DESC.WS_TERM_NOT_STANDARD = "S000";
        }
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_ONE_DAY);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_SEVEN_DAY);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_ONE_MONTH);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_THREE_MONTH);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_SIX_MONTH);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_ONE_YEAR);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_TWO_YEAR);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_THREE_YEAR);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_FIVE_YEAR);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_SIX_YEAR);
        CEP.TRC(SCCGWA, WS_TERM_DESC.WS_TERM_NOT_STANDARD);
        CEP.TRC(SCCGWA, TDCACIU.TERM_N);
        if (!TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_ONE_DAY) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_SEVEN_DAY) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_ONE_MONTH) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_THREE_MONTH) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_SIX_MONTH) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_ONE_YEAR) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_TWO_YEAR) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_THREE_YEAR) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_FIVE_YEAR) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_SIX_YEAR) 
            && !TDCACIU.TERM_N.equalsIgnoreCase(WS_TERM_DESC.WS_TERM_NOT_STANDARD) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM1.equalsIgnoreCase(TDCACIU.TERM_N) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM2.equalsIgnoreCase(TDCACIU.TERM_N) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM3.equalsIgnoreCase(TDCACIU.TERM_N) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM4.equalsIgnoreCase(TDCACIU.TERM_N) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM5.equalsIgnoreCase(TDCACIU.TERM_N) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CCY_TIME-1].TERM6.equalsIgnoreCase(TDCACIU.TERM_N)) {
            CEP.TRC(SCCGWA, "BBB");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_ERR);
        }
    }
    public void T000_STARTBR_BVT() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.upd = true;
        TDTBVT_BR.rp.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.STARTBR(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_DB_FLG = 'N';
        } else {
            WS_BVT_DB_FLG = 'Y';
        }
    }
    public void B240_GEN_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E143";
        SCCHMPW.INP_DATA.AC_FLG = '0';
        if (TDCACIU.MAIN_AC.trim().length() > 0) {
            SCCHMPW.INP_DATA.OLD_AC = TDCACIU.MAIN_AC;
            SCCHMPW.INP_DATA.NEW_AC = TDCACIU.MAIN_AC;
        } else {
            SCCHMPW.INP_DATA.OLD_AC = TDCACIU.AC_NO;
            SCCHMPW.INP_DATA.NEW_AC = TDCACIU.AC_NO;
        }
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.OLD_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.PIN_DA);
        S000_CALL_SCZHMPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B250_READ_UPDATE_TDTCDI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READ_UPDATE_TDTCDI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRCDI.PAY_AC);
        CEP.TRC(SCCGWA, TDRCDI.INTOUT);
        WS_TABLES_INFO.WS_CDI_INTOUT = TDRCDI.INTOUT;
        WS_TABLES_INFO.WS_CDI_PAY_AC = TDRCDI.PAY_AC;
        TDCACIU.PAY_AC_O = TDRCDI.PAY_AC;
        if (TDCACIU.PI_AC_N.trim().length() > 0 
            && TDCACIU.AUTO_FLG_N != '2' 
            && !WS_TABLES_INFO.WS_CDI_PAY_AC.equalsIgnoreCase(TDCACIU.PAY_AC_N)) {
            WS_DD_AC = TDCACIU.PI_AC_N;
            R000_CHK_CI_INFO();
            if (pgmRtn) return;
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                R000_CHK_DD_INFO();
                if (pgmRtn) return;
                B235_GET_CHK_DDAC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDCACIU.AUTO_FLG_N);
        CEP.TRC(SCCGWA, WS_TABLES_INFO.WS_CDI_PAY_AC);
        CEP.TRC(SCCGWA, TDCACIU.PI_AC_N);
        if (TDCACIU.PI_AC_N.trim().length() > 0) {
            CEP.TRC(SCCGWA, "33333333333333");
            TDRCDI.CD_AUTO_FLG = 'Y';
            TDRCDI.INTOUT = TDCACIU.AUTO_FLG_N;
            TDRCDI.REMMIT_BK = "" + TDCACIU.INT_BR_N;
            JIBS_tmp_int = TDRCDI.REMMIT_BK.length();
            for (int i=0;i<14-JIBS_tmp_int;i++) TDRCDI.REMMIT_BK = "0" + TDRCDI.REMMIT_BK;
            TDRCDI.REMMIT_NM = TDCACIU.INT_NM_N;
            TDRCDI.STL_MTH = '3';
            TDRCDI.PAY_AC = TDCACIU.PI_AC_N;
        } else {
            TDRCDI.CD_AUTO_FLG = 'N';
            TDRCDI.INTOUT = ' ';
            TDRCDI.REMMIT_BK = " ";
            TDRCDI.REMMIT_NM = " ";
            TDRCDI.STL_MTH = ' ';
            TDRCDI.PAY_AC = " ";
        }
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTCDI();
        if (pgmRtn) return;
    }
    public void B260_READ_UPDATE_TDTINST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRINST);
        if ((TDCACIU.INSTR_MTH_N == '3' 
            || TDCACIU.INSTR_MTH_N == '4' 
            || TDCACIU.INSTR_MTH_N == '5' 
            || TDCACIU.INSTR_MTH_N == '6') 
            && TDCACIU.TERM_N.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_TERM_M_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READ_UPDATE_TDTINST();
        if (pgmRtn) return;
        if (TDCACIU.PAY_AC_N.trim().length() > 0 
            && ((TDCACIU.INSTR_MTH_N == '1' 
            && !TDRINST.STL_AC.equalsIgnoreCase(TDCACIU.PAY_AC_N)) 
            || (TDCACIU.INSTR_MTH_N == '6' 
            && !TDRINST.STL_INT_AC.equalsIgnoreCase(TDCACIU.PAY_AC_N)))) {
            WS_DD_AC = TDCACIU.PAY_AC_N;
            R000_CHK_CI_INFO();
            if (pgmRtn) return;
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                R000_CHK_DD_INFO();
                if (pgmRtn) return;
            }
        }
        if (TDCACIU.INSTR_MTH_N != TDRINST.STL_MTH 
            && TDCACIU.INSTR_MTH_N != ' ') {
            B261_CHECK_INSTR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCACIU.NUM_N);
        if (TDCACIU.INSTR_MTH_N == '1') {
            TDRINST.STL_AC = TDCACIU.PAY_AC_N;
            TDRINST.STL_MTH = '2';
            TDRINST.STL_INT_AC = " ";
            TDRINST.STL_INT_MTH = ' ';
        }
        if (TDCACIU.INSTR_MTH_N == '6' 
            || TDCACIU.INSTR_MTH_N == '3') {
            if (TDCACIU.INSTR_MTH_N == '6') {
                TDRINST.STL_INT_AC = TDCACIU.PAY_AC_N;
                TDRINST.STL_INT_MTH = '2';
                TDRINST.STL_AC = " ";
                TDRINST.STL_MTH = ' ';
            }
            TDRINST.SER_TIME = TDCACIU.NUM_N;
        }
        if (TDCACIU.INSTR_MTH_N == '0') {
            TDRINST.STL_INT_AC = " ";
            TDRINST.STL_AC = " ";
            TDRINST.STL_INT_MTH = ' ';
            TDRINST.STL_MTH = ' ';
            TDRINST.SER_TIME = 0;
        }
        if (TDCACIU.INSTR_MTH_O != TDCACIU.INSTR_MTH_N 
            && (TDCACIU.INSTR_MTH_N == '4' 
            || TDCACIU.INSTR_MTH_N == '5')) {
        } else {
            if (TDCACIU.INSTR_MTH_N != '4' 
                && TDCACIU.INSTR_MTH_N != '5') {
                TDRINST.INSTR_AMT = 0;
            }
        }
        if (TDCACIU.INSTR_MTH_N == '3' 
            || TDCACIU.INSTR_MTH_N == '4' 
            || TDCACIU.INSTR_MTH_N == '5' 
            || TDCACIU.INSTR_MTH_N == '6') {
            TDRINST.INSTR_TERM = TDCACIU.TERM_N;
        } else {
            if (TDCACIU.INSTR_MTH_N == '0' 
                || TDCACIU.INSTR_MTH_N == '1') {
                TDRINST.INSTR_TERM = " ";
            }
        }
        TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, TDRINST.SER_TIME);
        if (WS_STS_FLG == 'Y') {
            T000_UPDATE_TDTINST();
            if (pgmRtn) return;
        } else {
            if (WS_STS_FLG == 'N' 
                && TDCACIU.INSTR_MTH_N != '0') {
                TDRINST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                TDRINST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRINST.PROD_CD = TDRSMST.PROD_CD;
                T000_WRITE_TDTINST();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_O);
        CEP.TRC(SCCGWA, TDCACIU.INSTR_MTH_N);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_O);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_N);
        CEP.TRC(SCCGWA, TDCACIU.TERM_O);
        CEP.TRC(SCCGWA, TDCACIU.TERM_N);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, WS_TD_CNM);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_O);
        CEP.TRC(SCCGWA, TDCACIU.PAY_AC_N);
        IBS.init(SCCGWA, TDCOACIU);
        TDCOACIU.BV_CD = TDCACIU.BV_CD;
        TDCOACIU.BV_TYP = TDCACIU.BV_TYP;
        TDCOACIU.BV_NO = TDCACIU.BV_NO;
        TDCOACIU.MAIN_AC = TDCACIU.MAIN_AC;
        TDCOACIU.AC_SEQ = TDCACIU.AC_SEQ;
        TDCOACIU.AC_NO = TDCACIU.AC_NO;
        TDCOACIU.INSTR_MTH_O = WS_TABLES_INFO.WS_SMST_INSTR_MTH;
        if (TDCACIU.INSTR_MTH_N != ' ') {
            TDCOACIU.INSTR_MTH_N = TDCACIU.INSTR_MTH_N;
        } else {
            TDCOACIU.INSTR_MTH_N = WS_TABLES_INFO.WS_SMST_INSTR_MTH;
        }
        CEP.TRC(SCCGWA, WS_TABLES_INFO.WS_CDI_PAY_AC);
        TDCOACIU.PAY_AC_O = WS_TABLES_INFO.WS_CDI_PAY_AC;
        if (TDCACIU.PAY_AC_N.trim().length() > 0) {
            TDCOACIU.PAY_AC_N = TDCACIU.PAY_AC_N;
        } else {
            TDCOACIU.PAY_AC_N = WS_TABLES_INFO.WS_CDI_PAY_AC;
        }
        CEP.TRC(SCCGWA, "12343");
        TDCOACIU.TERM_O = TDCACIU.TERM_O;
        TDCOACIU.TERM_N = TDRINST.INSTR_TERM;
        TDCOACIU.NUM_N = TDRINST.SER_TIME;
        TDCOACIU.NUM_O = TDCACIU.NUM_O;
        TDCOACIU.AUTO_FLG_N = TDRCDI.INTOUT;
        JIBS_NumStr = "" + TDCACIU.NUM_O;
        TDCOACIU.AUTO_FLG_O = JIBS_NumStr.charAt(0);
        TDCOACIU.AC_TYP_O = TDCACIU.AC_TYP_O;
        TDCOACIU.AC_TYP_N = TDCACIU.AC_TYP_N;
        TDCOACIU.OIC_NO_O = TDCACIU.OIC_NO_O;
        TDCOACIU.OIC_NO_N = TDCACIU.OIC_NO_N;
        TDCOACIU.RES_CD_O = TDCACIU.RES_CD_O;
        TDCOACIU.RES_CD_N = TDCACIU.RES_CD_N;
        TDCOACIU.SUB_NO_O = TDCACIU.SUB_NO_O;
        TDCOACIU.SUB_NO_N = TDCACIU.SUB_NO_N;
        TDCOACIU.FC_NO_O = TDCACIU.FC_NO_O;
        TDCOACIU.FC_NO_N = TDCACIU.FC_NO_N;
        TDCOACIU.FC_CD_O = TDCACIU.FC_CD_O;
        TDCOACIU.FC_CD_N = TDCACIU.FC_CD_N;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            TDCOACIU.PRV_RAT_N = TDCACIU.PRV_RAT_N;
            TDCOACIU.OVE_RAT_N = TDCACIU.OVE_RAT_N;
            TDCOACIU.DUE_RAT_N = TDCACIU.DUE_RAT_N;
        }
        B230_WRI_NFIN_HIS_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_UPD_ACIU_FMT;
        SCCFMT.DATA_PTR = TDCOACIU;
        SCCFMT.DATA_LEN = 976;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_UPD_ACIU_FMT;
        BPCPNHIS.INFO.TX_TYP_CD = "PB01";
        if (TDCACIU.MAIN_AC.trim().length() > 0) {
            BPCPNHIS.INFO.AC = TDCACIU.MAIN_AC;
        } else {
            BPCPNHIS.INFO.AC = TDCACIU.AC_NO;
        }
        CEP.TRC(SCCGWA, TDCACIU.AC_NO);
        CEP.TRC(SCCGWA, TDCACIU.MAIN_AC);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.AC_SEQ = TDCACIU.AC_SEQ;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHK_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_DD_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
            if (!CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NOT_SAME);
            }
            if ((CICQACRI.O_DATA.O_CI_TYP == '2' 
                || CICQACRI.O_DATA.O_CI_TYP == '3') 
                && WS_CI_TYP == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_TYPE_M_ST);
            }
            if (CICQACRI.O_DATA.O_CI_TYP == '1' 
                && (WS_CI_TYP == '2' 
                || WS_CI_TYP == '3')) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC);
            }
        }
    }
    public void R000_CHK_DD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_DD_AC;
        DDCIQBAL.DATA.CCY = TDRSMST.CCY;
        DDCIQBAL.DATA.CCY_TYPE = TDRSMST.CCY_TYP;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_SP_FROZEN);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_LEG_FROZEN2);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_INNER_HOLD);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_SIFA_HOLD);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (WS_DD_AC == null) WS_DD_AC = "";
        JIBS_tmp_int = WS_DD_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_DD_AC += " ";
        if (TDCACIU.AC_NO == null) TDCACIU.AC_NO = "";
        JIBS_tmp_int = TDCACIU.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACIU.AC_NO += " ";
        if (WS_DD_AC == null) WS_DD_AC = "";
        JIBS_tmp_int = WS_DD_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_DD_AC += " ";
        if (!WS_DD_AC.substring(0, 3).equalsIgnoreCase(TDCACIU.AC_NO.substring(0, 3)) 
            && !IBS.isNumeric(WS_DD_AC.substring(0, 1)) 
            && TDCACIU.AC_NO.trim().length() > 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            WS_RC_MSG.WS_RC = SCCCLDT.RC;
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_RC_MSG);
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR  " + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "END_DATE = :TDRIREV.END_DATE";
        TDTIREV_RD.upd = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_UPDATE_TA_ERR);
        }
    }
    public void T000_READ_UPDATE_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        TDTINST_RD.upd = true;
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void T000_REWRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.REWRITE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_UPDATE_TA_ERR);
        }
    }
    public void T000_UPDATE_TDTINST() throws IOException,SQLException,Exception {
        TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRINST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.REWRITE(SCCGWA, TDRINST, TDTINST_RD);
    }
    public void T000_WRITE_TDTINST() throws IOException,SQLException,Exception {
        TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRINST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.WRITE(SCCGWA, TDRINST, TDTINST_RD);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (TDCACIU.OTHE_CHG != 'O') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            } else {
                IBS.CPY2CLS(SCCGWA, "TD3164", TDCACIU.RC);
            }
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_DCCUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READU_TDTAINT() throws IOException,SQLException,Exception {
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        TDTAINT_RD.upd = true;
        IBS.READ(SCCGWA, TDRAINT, TDTAINT_RD);
    }
    public void T000_UPDATE_TDTAINT() throws IOException,SQLException,Exception {
        TDRAINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRAINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRAINT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        IBS.REWRITE(SCCGWA, TDRAINT, TDTAINT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_WRITE_TDTAINT() throws IOException,SQLException,Exception {
        TDRAINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRAINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRAINT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        IBS.WRITE(SCCGWA, TDRAINT, TDTAINT_RD);
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (TDCACIU.OTHE_CHG != 'O') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            } else {
                IBS.CPY2CLS(SCCGWA, "TD3164", TDCACIU.RC);
            }
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READ_TDTBVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READ_UPDATE_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        TDTCDI_RD.upd = true;
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTCDI() throws IOException,SQLException,Exception {
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.REWRITE(SCCGWA, TDRCDI, TDTCDI_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READ_BVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
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
