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

public class TDZLYBT {
    DBParm TDTONAC_RD;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT1 = "TD218";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    int WS_NUM1 = 0;
    int WS_NUM2 = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    char WS_CCY_FOUND = ' ';
    String WS_CHNL_NO = " ";
    int WS_BR = 0;
    short WS_LM_POINT_C = 0;
    char WS_TDTLMT_FLG = ' ';
    char WS_LMT_1_FLG = ' ';
    char WS_ZHIHANG_FLG = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_EFF_TYP = ' ';
    char WS_OTHE_FND = ' ';
    TDZLYBT_CP_PROD_CD CP_PROD_CD = new TDZLYBT_CP_PROD_CD();
    short WS_TIME = 0;
    int WS_COUNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
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
    TDRONAC TDRONAC = new TDRONAC();
    TDCOLYBT TDCOLYBT = new TDCOLYBT();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    TDCLYBT TDCLYBT;
    public void MP(SCCGWA SCCGWA, TDCLYBT TDCLYBT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCLYBT = TDCLYBT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZLYBT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCLYBT.ACO_AC);
        IBS.init(SCCGWA, TDRONAC);
        TDRONAC.OLD_AC_NO = TDCLYBT.ACO_AC;
        T000_READ_TDTONAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRONAC.KEY.ACO_AC);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDRONAC.KEY.ACO_AC;
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP != '4') {
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDRSMST.AC_NO;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
            if (TDRCMST.BV_TYP != '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
        CEP.TRC(SCCGWA, TDRONAC.CI_NO);
        IBS.init(SCCGWA, CICCUST);
        if (TDRONAC.CI_NO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = TDRONAC.CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        CEP.TRC(SCCGWA, TDRONAC.SEQ);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, TDRONAC.CI_NO);
        TDCOLYBT.CCY = TDRSMST.CCY;
        TDCOLYBT.CCY_TYP = TDRSMST.CCY_TYP;
        TDCOLYBT.PROD_CD = TDRSMST.PROD_CD;
        TDCOLYBT.SEQ = TDRONAC.SEQ;
        TDCOLYBT.ACO_AC = TDRONAC.KEY.ACO_AC;
        TDCOLYBT.AC_NO = TDRSMST.AC_NO;
        TDCOLYBT.CI_NM = CICCUST.O_DATA.O_CI_NM;
        TDCOLYBT.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        TDCOLYBT.ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCOLYBT.CI_NO = TDRONAC.CI_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDCOLYBT;
        SCCFMT.DATA_LEN = 414;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_TDTONAC() throws IOException,SQLException,Exception {
        TDTONAC_RD = new DBParm();
        TDTONAC_RD.TableName = "TDTONAC";
        TDTONAC_RD.where = "OLD_AC_NO = :TDRONAC.OLD_AC_NO";
        TDTONAC_RD.fst = true;
        IBS.READ(SCCGWA, TDRONAC, this, TDTONAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMT_1_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "ACO_AC = :TDRSMST.KEY.ACO_AC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMT_1_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LMT_1_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
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
