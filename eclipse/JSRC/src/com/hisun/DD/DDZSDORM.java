package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDORM {
    brParm DDTDORM_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT1 = "DD602";
    String K_OUTPUT_FMT2 = "DD603";
    char K_REQ_STS_DELETE = 'D';
    String WS_ERR_MSG = " ";
    DDZSDORM_WS_OUTPUT_INF WS_OUTPUT_INF = new DDZSDORM_WS_OUTPUT_INF();
    char WS_DORM_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRDORM DDRDORM = new DDRDORM();
    DDCODORM DDCODORM = new DDCODORM();
    DDCIDORM DDCIDORM = new DDCIDORM();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    int WS_STR_DT = 0;
    int WS_END_DT2 = 0;
    char WS_TYPE1 = ' ';
    char WS_TYPE2 = ' ';
    char WS_STS1 = ' ';
    char WS_STS2 = ' ';
    char WS_TSK_STS1 = ' ';
    char WS_TSK_STS2 = ' ';
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DDCSDORM DDCSDORM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCSDORM DDCSDORM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDORM = DDCSDORM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSDORM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        B060_CHECK_AUTHORITY();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDORM.FUNC);
        if (DDCSDORM.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDORM.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDORM.FUNC == 'M') {
            B030_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDORM.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDORM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSDORM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDORM);
        DDCIDORM.DATA.DATE = DDCSDORM.DATE;
        DDCIDORM.DATA.BR_NO = DDCSDORM.BR_NO;
        DDCIDORM.DATA.TYPE = DDCSDORM.TYPE;
        DDCIDORM.OPT = 'I';
        S000_CALL_DDZIDORM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDORM);
        DDCIDORM.DATA.DATE = DDCSDORM.DATE;
        DDCIDORM.DATA.BR_NO = DDCSDORM.BR_NO;
        DDCIDORM.DATA.TYPE = DDCSDORM.TYPE;
        DDCIDORM.DATA.END_DT = DDCSDORM.END_DT;
        DDCIDORM.DATA.REMARKS = DDCSDORM.REMARKS;
        DDCIDORM.DATA.FLG1 = DDCSDORM.FLG1;
        DDCIDORM.DATA.FLG2 = DDCSDORM.FLG2;
        DDCIDORM.OPT = 'A';
        S000_CALL_DDZIDORM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDORM);
        DDCIDORM.DATA.DATE = DDCSDORM.DATE;
        DDCIDORM.DATA.BR_NO = DDCSDORM.BR_NO;
        DDCIDORM.DATA.TYPE = DDCSDORM.TYPE;
        DDCIDORM.DATA.END_DT = DDCSDORM.END_DT;
        DDCIDORM.DATA.REMARKS = DDCSDORM.REMARKS;
        DDCIDORM.OPT = 'U';
        S000_CALL_DDZIDORM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDORM);
        DDCIDORM.DATA.DATE = DDCSDORM.DATE;
        DDCIDORM.DATA.BR_NO = DDCSDORM.BR_NO;
        DDCIDORM.DATA.TYPE = DDCSDORM.TYPE;
        DDCIDORM.DATA.DEL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDORM.DATA.DEL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCIDORM.DATA.AUT_TLR2 = SCCGWA.COMM_AREA.SUP1_ID;
        DDCIDORM.DATA.STS = K_REQ_STS_DELETE;
        DDCIDORM.OPT = 'S';
        S000_CALL_DDZIDORM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B050_10_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B050_20_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B050_30_STARTBR_DDTDORM();
        if (pgmRtn) return;
        B050_40_READNEXT_DDTDORM();
        if (pgmRtn) return;
        while (WS_DORM_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_50_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B050_40_READNEXT_DDTDORM();
            if (pgmRtn) return;
        }
        B050_60_ENDBR_DDTDORM();
        if (pgmRtn) return;
    }
    public void B050_10_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DDCSDORM.BR_NO == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCSDORM.STR_DT == 0 
            || DDCSDORM.STR_DT > SCCGWA.COMM_AREA.AC_DATE 
            || DDCSDORM.STR_DT > DDCSDORM.END_DT2) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DT_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCSDORM.END_DT2 == 0 
            || DDCSDORM.END_DT2 > SCCGWA.COMM_AREA.AC_DATE 
            || DDCSDORM.END_DT2 < DDCSDORM.STR_DT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_DT2_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCSDORM.TYPE != ' ' 
            && DDCSDORM.TYPE != '1' 
            && DDCSDORM.TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REQ_TYP_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCSDORM.STS != ' ' 
            && DDCSDORM.STS != 'N' 
            && DDCSDORM.STS != 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_STS_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B050_20_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 192;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_30_STARTBR_DDTDORM() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTDORM();
        if (pgmRtn) return;
    }
    public void B050_40_READNEXT_DDTDORM() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTDORM();
        if (pgmRtn) return;
    }
    public void B050_60_ENDBR_DDTDORM() throws IOException,SQLException,Exception {
        T000_ENDBR_DDTDORM();
        if (pgmRtn) return;
    }
    public void B050_50_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_INF);
        WS_OUTPUT_INF.WS_BR_NO = DDRDORM.KEY.BR_NO;
        WS_OUTPUT_INF.WS_DATE = DDRDORM.KEY.DATE;
        WS_OUTPUT_INF.WS_END_DT = DDRDORM.END_DT;
        WS_OUTPUT_INF.WS_TYPE = DDRDORM.KEY.TYPE;
        WS_OUTPUT_INF.WS_TRS_BR = DDRDORM.TRS_BR;
        WS_OUTPUT_INF.WS_TRS_TLR = DDRDORM.TRS_TLR;
        WS_OUTPUT_INF.WS_AUT_TLR1 = DDRDORM.AUT_TLR1;
        WS_OUTPUT_INF.WS_DEL_BR = DDRDORM.DEL_BR;
        WS_OUTPUT_INF.WS_DEL_TLR = DDRDORM.DEL_TLR;
        WS_OUTPUT_INF.WS_AUT_TLR2 = DDRDORM.AUT_TLR2;
        WS_OUTPUT_INF.WS_REMARKS = DDRDORM.REMARKS;
        WS_OUTPUT_INF.WS_FLG1 = DDRDORM.FLG1;
        WS_OUTPUT_INF.WS_FLG2 = DDRDORM.FLG2;
        WS_OUTPUT_INF.WS_STS = DDRDORM.STS;
        WS_OUTPUT_INF.WS_TSK_STS = DDRDORM.TSK_STS;
        CEP.TRC(SCCGWA, DDRDORM.KEY.BR_NO);
        CEP.TRC(SCCGWA, DDRDORM.KEY.DATE);
        CEP.TRC(SCCGWA, DDRDORM.END_DT);
        CEP.TRC(SCCGWA, DDRDORM.KEY.TYPE);
        CEP.TRC(SCCGWA, DDRDORM.STS);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_INF);
        SCCMPAG.DATA_LEN = 192;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_CHECK_AUTHORITY() throws IOException,SQLException,Exception {
        if (DDCSDORM.BR_NO == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
        } else {
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTDORM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDORM);
        DDRDORM.KEY.BR_NO = DDCSDORM.BR_NO;
        WS_STR_DT = DDCSDORM.STR_DT;
        WS_END_DT2 = DDCSDORM.END_DT2;
        if (DDCSDORM.TYPE == ) {
            WS_TYPE1 = '1';
            WS_TYPE2 = '2';
        } else if (DDCSDORM.TYPE == '1') {
            WS_TYPE1 = '1';
        } else if (DDCSDORM.TYPE == '2') {
            WS_TYPE2 = '2';
        } else {
        }
        if (DDCSDORM.STS == ) {
            WS_STS1 = 'N';
            WS_STS2 = 'D';
        } else if (DDCSDORM.STS == 'N') {
            WS_STS1 = 'N';
        } else if (DDCSDORM.STS == 'D') {
            WS_STS2 = 'D';
        } else {
        }
        if (DDCSDORM.TSK_STS == ) {
            WS_TSK_STS1 = 'W';
            WS_TSK_STS2 = 'S';
        } else if (DDCSDORM.TSK_STS == 'W') {
            WS_TSK_STS1 = 'W';
        } else if (DDCSDORM.TSK_STS == 'S') {
            WS_TSK_STS2 = 'S';
        } else {
        }
        WS_DORM_FLG = 'N';
        CEP.TRC(SCCGWA, DDCSDORM.BR_NO);
        CEP.TRC(SCCGWA, DDCSDORM.STR_DT);
        CEP.TRC(SCCGWA, DDCSDORM.END_DT2);
        CEP.TRC(SCCGWA, DDCSDORM.TYPE);
        CEP.TRC(SCCGWA, DDCSDORM.STS);
        CEP.TRC(SCCGWA, DDCSDORM.TSK_STS);
        DDTDORM_BR.rp = new DBParm();
        DDTDORM_BR.rp.TableName = "DDTDORM";
        DDTDORM_BR.rp.where = "BR_NO = :DDRDORM.KEY.BR_NO "
            + "AND DATE >= :WS_STR_DT "
            + "AND DATE <= :WS_END_DT2 "
            + "AND ( TYPE = :WS_TYPE1 "
            + "OR TYPE = :WS_TYPE2 ) "
            + "AND ( STS = :WS_STS1 "
            + "OR STS = :WS_STS2 ) "
            + "AND ( TSK_STS = :WS_TSK_STS1 "
            + "OR TSK_STS = :WS_TSK_STS2 )";
        DDTDORM_BR.rp.order = "DATE , BR_NO";
        IBS.STARTBR(SCCGWA, DDRDORM, this, DDTDORM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTDORM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDORM, this, DDTDORM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DORM_FLG = 'Y';
        } else {
            WS_DORM_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTDORM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDORM_BR);
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDORM.BR_NO);
        CEP.TRC(SCCGWA, DDCSDORM.DATE);
        CEP.TRC(SCCGWA, DDCSDORM.END_DT);
        CEP.TRC(SCCGWA, DDCSDORM.TYPE);
        if (DDCSDORM.BR_NO == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCSDORM.FUNC == 'I') {
            if (DDCSDORM.DATE == 0 
                || DDCSDORM.DATE > SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REQ_DT_INVALID;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (DDCSDORM.FUNC == 'A' 
            && DDCSDORM.DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_DT_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCSDORM.FUNC == 'A' 
            || DDCSDORM.FUNC == 'M' 
            || DDCSDORM.FUNC == 'D') {
            if (DDCSDORM.END_DT == 0 
                || DDCSDORM.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_DT_INVALID;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (DDCSDORM.TYPE == ' ' 
            || (DDCSDORM.TYPE != '1' 
            && DDCSDORM.TYPE != '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REQ_TYP_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCODORM);
        DDCODORM.DATE = DDCIDORM.DATA.DATE;
        DDCODORM.BR_NO = DDCIDORM.DATA.BR_NO;
        DDCODORM.TYPE = DDCIDORM.DATA.TYPE;
        DDCODORM.END_DT = DDCIDORM.DATA.END_DT;
        DDCODORM.TRS_BR = DDCIDORM.DATA.TRS_BR;
        DDCODORM.TRS_TLR = DDCIDORM.DATA.TRS_TLR;
        DDCODORM.AUT_TLR1 = DDCIDORM.DATA.AUT_TLR1;
        DDCODORM.DEL_BR = DDCIDORM.DATA.DEL_BR;
        DDCODORM.DEL_TLR = DDCIDORM.DATA.DEL_TLR;
        DDCODORM.AUT_TLR2 = DDCIDORM.DATA.AUT_TLR2;
        DDCODORM.REMARKS = DDCIDORM.DATA.REMARKS;
        DDCODORM.FLG1 = DDCIDORM.DATA.FLG1;
        DDCODORM.FLG2 = DDCIDORM.DATA.FLG2;
        DDCODORM.STS = DDCIDORM.DATA.STS;
        DDCODORM.TSK_STS = DDCIDORM.DATA.TSK_STS;
        CEP.TRC(SCCGWA, DDCODORM);
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, DDCSDORM.FUNC);
        if (DDCSDORM.FUNC == 'I') {
            CEP.TRC(SCCGWA, "NEXT");
            SCCFMT.FMTID = K_OUTPUT_FMT1;
        } else {
            CEP.TRC(SCCGWA, "FINIAL");
            SCCFMT.FMTID = K_OUTPUT_FMT2;
        }
        SCCFMT.DATA_PTR = DDCODORM;
        SCCFMT.DATA_LEN = 192;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = DDCSDORM.BR_NO;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DDCSDORM.BR_NO;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if ((BPCPRGST.FLAG == 'Y' 
            && BPCPRGST.LVL_RELATION == 'A') 
            || (BPCPRGST.BRANCH_FLG == 'Y' 
            && BPCPORUP.DATA_INFO.ATTR == '1' 
            && BPCPQORG.ATTR == '0')) {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_OPT_AUT_LIMIT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZIDORM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDORM", DDCIDORM);
        if (DDCIDORM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDORM.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
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
