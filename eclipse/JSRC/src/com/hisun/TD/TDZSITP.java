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

public class TDZSITP {
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    brParm TDTDINT_BR = new brParm();
    DBParm TDTDINT_RD;
    DBParm TDTINST_RD;
    DBParm TDTBVT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_EFF_TYP = ' ';
    char WS_DINT_FND = ' ';
    short WS_TIME = 0;
    String WS_ACO_AC = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
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
    TDCOITP TDCOITP = new TDCOITP();
    TDRDINT TDRDINT = new TDRDINT();
    TDCOSITP TDCOSITP = new TDCOSITP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    TDCSITP TDCSITP;
    public void MP(SCCGWA SCCGWA, TDCSITP TDCSITP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSITP = TDCSITP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSITP return!");
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
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSITP.AC_NO);
        if (TDCSITP.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
        }
        CEP.TRC(SCCGWA, TDCSITP.BV_NO);
        CEP.TRC(SCCGWA, TDCSITP.BV_TYP);
        if (TDCSITP.BV_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
        }
    }
    public void B020_MSG_CI_DATA() throws IOException,SQLException,Exception {
        if (TDCSITP.BV_TYP != '4') {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCSITP.AC_NO;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCSITP.AC_NO;
        CICQACAC.DATA.BV_NO = TDCSITP.BV_NO;
        CICQACAC.DATA.AGR_SEQ = TDCSITP.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        R000_CHECK_BV_PROC();
        if (pgmRtn) return;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRDINT);
        TDRDINT.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_TITTLE_LIST();
        if (pgmRtn) return;
        T000_STARTBR_TDTDINT();
        if (pgmRtn) return;
        T000_READNEXT_TDTDINT();
        if (pgmRtn) return;
        WS_TIME = 1;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, TDCOSITP);
            B030_OUTPUT_DATA();
            if (pgmRtn) return;
            R000_MPAG_OUT();
            if (pgmRtn) return;
            T000_READNEXT_TDTDINT();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTDINT();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRDINT.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRDINT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, TDRDINT.KEY.JRNNO);
        CEP.TRC(SCCGWA, TDRDINT.KEY.SEQ);
        CEP.TRC(SCCGWA, TDRDINT.BR);
        CEP.TRC(SCCGWA, TDRDINT.CCY);
        CEP.TRC(SCCGWA, TDRDINT.RAT_INT);
        CEP.TRC(SCCGWA, TDRDINT.TAX_RAT);
        CEP.TRC(SCCGWA, TDRDINT.BAL);
        CEP.TRC(SCCGWA, TDRDINT.VAL_DATE);
        CEP.TRC(SCCGWA, TDRDINT.EXP_DATE);
        CEP.TRC(SCCGWA, TDRDINT.AMT);
        CEP.TRC(SCCGWA, TDRDINT.TAX_AMT);
        CEP.TRC(SCCGWA, TDRDINT.STR_DATE);
        CEP.TRC(SCCGWA, TDRDINT.END_DATE);
        CEP.TRC(SCCGWA, TDRDINT.INT_TYPE);
        CEP.TRC(SCCGWA, TDRDINT.STATUS);
        CEP.TRC(SCCGWA, TDRDINT.OWNER_BK);
        CEP.TRC(SCCGWA, TDRDINT.CRT_TLR);
        CEP.TRC(SCCGWA, TDRDINT.CRT_DATE);
        CEP.TRC(SCCGWA, TDRDINT.UPD_TLT);
        CEP.TRC(SCCGWA, TDRDINT.UPD_DATE);
        CEP.TRC(SCCGWA, TDRDINT.UPD_TIME);
        TDCOSITP.AC_NO = TDCSITP.AC_NO;
        TDCOSITP.ACO_AC = TDRDINT.KEY.ACO_AC;
        TDCOSITP.AC_DATE = TDRDINT.KEY.AC_DATE;
        TDCOSITP.JRNNO = TDRDINT.KEY.JRNNO;
        TDCOSITP.SEQ = TDRDINT.KEY.SEQ;
        TDCOSITP.BR = TDRDINT.BR;
        TDCOSITP.CCY = TDRDINT.CCY;
        TDCOSITP.RAT_INT = TDRDINT.RAT_INT;
        TDCOSITP.TAX_RAT = TDRDINT.TAX_RAT;
        TDCOSITP.BAL = TDRDINT.BAL;
        TDCOSITP.VAL_DATE = TDRDINT.VAL_DATE;
        TDCOSITP.EXP_DATE = TDRDINT.EXP_DATE;
        TDCOSITP.AMT = TDRDINT.AMT;
        TDCOSITP.TAX_AMT = TDRDINT.TAX_AMT;
        TDCOSITP.STR_DATE = TDRDINT.STR_DATE;
        TDCOSITP.END_DATE = TDRDINT.END_DATE;
        TDCOSITP.INT_TYP = TDRDINT.INT_TYPE;
        TDCOSITP.STATUS = TDRDINT.STATUS;
        TDCOSITP.OWNER_BK = TDRDINT.OWNER_BK;
        TDCOSITP.CRT_TLR = TDRDINT.CRT_TLR;
        TDCOSITP.CRT_DATE = TDRDINT.CRT_DATE;
        TDCOSITP.UPD_TLR = TDRDINT.UPD_TLT;
        TDCOSITP.UPD_DATE = TDRDINT.UPD_DATE;
        TDCOSITP.UPD_TIME = TDRDINT.UPD_TIME;
    }
    public void R000_MPAG_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDCOSITP);
        SCCMPAG.DATA_LEN = 246;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
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
    public void R000_CHECK_BV_PROC() throws IOException,SQLException,Exception {
        if (TDCSITP.BV_NO.trim().length() > 0) {
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDCSITP.AC_NO;
            R000_READ_TDTBVT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRBVT.BV_NO);
            if (!TDCSITP.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
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
    public void T000_STARTBR_TDTDINT() throws IOException,SQLException,Exception {
        TDTDINT_BR.rp = new DBParm();
        TDTDINT_BR.rp.TableName = "TDTDINT";
        TDTDINT_BR.rp.where = "ACO_AC = :TDRDINT.KEY.ACO_AC "
            + "AND AC_DATE >= :TDRDINT.KEY.AC_DATE "
            + "AND JRNNO >= :TDRDINT.KEY.JRNNO";
        TDTDINT_BR.rp.order = "AC_DATE , JRNNO";
        IBS.STARTBR(SCCGWA, TDRDINT, this, TDTDINT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_DINT_FND = 'N';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            }
        }
    }
    public void T000_READNEXT_TDTDINT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRDINT, this, TDTDINT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_DINT_FND = 'N';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            }
        }
    }
    public void T000_ENDBR_TDTDINT() throws IOException,SQLException,Exception {
        TDTDINT_RD = new DBParm();
        TDTDINT_RD.TableName = "TDTDINT";
        IBS.READ(SCCGWA, TDRDINT, TDTDINT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_TITTLE_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void R000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            R000_READ_TDTBVT_SMST();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_TDTBVT_SMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = WS_ACO_AC;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
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
