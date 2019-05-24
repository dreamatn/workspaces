package com.hisun.TD;

import com.hisun.BP.*;
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
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSPFT {
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTINST_RD;
    DBParm TDTIREV_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TD_KD_SEQ = "KDSEQ";
    String K_OUTPUT_FMT1 = "TD016";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_EFF_TYP = ' ';
    short WS_TIME = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDRIREV TDRIREV = new TDRIREV();
    TDRINST TDRINST = new TDRINST();
    TDRBVT TDRBVT = new TDRBVT();
    CICCUST CICCUST = new CICCUST();
    CICQACAC CICQACAC = new CICQACAC();
    TDCOPFT TDCOPFT = new TDCOPFT();
    SCCGWA SCCGWA;
    TDCSPFT TDCSPFT;
    public void MP(SCCGWA SCCGWA, TDCSPFT TDCSPFT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSPFT = TDCSPFT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSPFT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MSG_CI_DATA();
        if (pgmRtn) return;
        B030_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSPFT.AC_NO);
        if (TDCSPFT.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
        }
        CEP.TRC(SCCGWA, TDCSPFT.P_CNT);
        if (TDCSPFT.P_CNT == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
        }
    }
    public void B020_MSG_CI_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCSPFT.AC_NO;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDCSPFT.AC_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCSPFT.AC_NO;
        CICQACAC.DATA.AGR_SEQ = TDCSPFT.AC_SEQ;
        if (TDRCMST.BV_TYP != '8') {
            CICQACAC.DATA.BV_NO = TDCSPFT.BV_NO;
        }
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP != ' ' 
            && TDRCMST.BV_TYP == '0') {
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
        }
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        T000_READ_TDTBVT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_TDTIREV();
        if (pgmRtn) return;
        if (TDRSMST.INSTR_MTH != '0') {
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
            if (pgmRtn) return;
        }
        TDRSMST.HOLD_NUM += TDCSPFT.P_CNT;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
        CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DATE);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, TDRSMST.TERM);
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRINST.STL_INT_AC);
        CEP.TRC(SCCGWA, TDRINST.INT_INOUT);
        CEP.TRC(SCCGWA, TDRINST.INT_REMMIT_BK);
        CEP.TRC(SCCGWA, TDRINST.INT_REMMIT_NM);
        CEP.TRC(SCCGWA, TDRIREV.RATE_SEL);
        CEP.TRC(SCCGWA, TDRIREV.INT_RUL_CD);
        CEP.TRC(SCCGWA, TDRIREV.RATE_TYPE);
        CEP.TRC(SCCGWA, TDRIREV.TENOR);
        CEP.TRC(SCCGWA, TDRIREV.SPRD_PNT);
        CEP.TRC(SCCGWA, TDRIREV.SPRD_PCT);
        CEP.TRC(SCCGWA, TDRIREV.ACTI_NO);
        CEP.TRC(SCCGWA, TDRCMST.FRG_IND);
        TDCOPFT.PRDAC_CD = TDRSMST.PRDAC_CD;
        if (TDRCMST.BV_TYP != '0') {
            TDCOPFT.BV_TYP = TDRCMST.BV_TYP;
        } else {
            if (TDRSMST.BV_TYP != ' ' 
                && TDRCMST.BV_TYP == '0') {
                TDCOPFT.BV_TYP = TDRSMST.BV_TYP;
            }
        }
        TDCOPFT.BV_CD = TDRBVT.BV_CD;
        TDCOPFT.BV_NO = TDRBVT.BV_NO;
        TDCOPFT.MAIN_AC = TDRCMST.KEY.AC_NO;
        TDCOPFT.MAIN_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDCOPFT.NAME = CICCUST.O_DATA.O_CI_NM;
        TDCOPFT.TXN_AMT_S = TDRSMST.BAL;
        TDCOPFT.CCY = TDRSMST.CCY;
        TDCOPFT.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCOPFT.VAL_DATE = TDRSMST.VAL_DATE;
        TDCOPFT.EXP_DATE = TDRSMST.EXP_DATE;
        TDCOPFT.TERMS = TDRSMST.TERM;
        TDCOPFT.INT_RAT = TDRIREV.CON_RATE;
        TDCOPFT.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCOPFT.BR = TDRCMST.OWNER_BR;
        TDCOPFT.INT_AC = TDRINST.STL_INT_AC;
        TDCOPFT.INOUT = TDRINST.INT_INOUT;
        TDCOPFT.REMMIT_BK = TDRINST.INT_REMMIT_BK;
        TDCOPFT.REMMIT_NM = TDRINST.INT_REMMIT_NM;
        TDCOPFT.INT_SEL = TDRIREV.RATE_SEL;
        TDCOPFT.AC_RUL_CD = TDRIREV.INT_RUL_CD;
        TDCOPFT.RATE_TYP = TDRIREV.RATE_TYPE;
        TDCOPFT.TENOR = TDRIREV.TENOR;
        TDCOPFT.SPRD_PNT = TDRIREV.SPRD_PNT;
        TDCOPFT.SPRD_PCT = TDRIREV.SPRD_PCT;
        TDCOPFT.FRG_IND = TDRCMST.FRG_IND;
        TDCOPFT.ACTI_NO = TDRIREV.ACTI_NO;
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDCOPFT;
        SCCFMT.DATA_LEN = 1453;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE > :TDRIREV.END_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.HOLD_NUM);
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
