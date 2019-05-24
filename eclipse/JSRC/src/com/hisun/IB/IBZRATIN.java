package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZRATIN {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTINRH_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    char K_RATE_INQ = 'I';
    char K_RATE_MOD = 'M';
    char K_INT_SETTL = 'S';
    String K_OUTPUT_FMT = "IBB11";
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    IBCQINF IBCQINF = new IBCQINF();
    IBCSOINT IBCSOINT = new IBCSOINT();
    DDCSLLCX DDCSLLCX = new DDCSLLCX();
    IBRMST IBRMST = new IBRMST();
    IBRINRH IBRINRH = new IBRINRH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCRATIN IBCRATIN;
    public void MP(SCCGWA SCCGWA, IBCRATIN IBCRATIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCRATIN = IBCRATIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZRATIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCRATIN.RC.RC_MMO = " ";
        IBCRATIN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_RATE_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCRATIN.FUNC);
        if (IBCRATIN.FUNC == K_RATE_INQ) {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (IBCRATIN.FUNC == K_RATE_MOD) {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (IBCRATIN.FUNC == K_INT_SETTL) {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCRATIN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCRATIN.AC_NO);
        CEP.TRC(SCCGWA, IBCRATIN.NOS_CD);
        CEP.TRC(SCCGWA, IBCRATIN.CCY);
        if ((IBCRATIN.NOS_CD.trim().length() == 0 
            || IBCRATIN.CCY.trim().length() == 0) 
            && IBCRATIN.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCRATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_RATE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCRATIN.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCRATIN.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCRATIN.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCRATIN.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
            && IBCRATIN.FUNC == 'I') {
            B020_01_GET_AC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if ((BPCPQORG.VIL_TYP.equalsIgnoreCase("00") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706660500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("51") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706670500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("52") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706671500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("53") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706672500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("54") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706673500)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INQ_NOT_ALLOW, IBCRATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCRATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B040_01_CHECK_AC_ATTR();
        if (pgmRtn) return;
    }
    public void B040_01_CHECK_AC_ATTR() throws IOException,SQLException,Exception {
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L') {
            if (IBCRATIN.FUNC == 'M') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_MAINT_ALLOW_N, IBCRATIN.RC);
            } else {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INT_SETT_ALLOW_N, IBCRATIN.RC);
            }
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBRINRH.KEY.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, IBRINRH.KEY.VALUE_DATE);
        T000_READ_INRH_LAST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCSOINT);
        IBCSOINT.DATA.NOST_CODE = IBCQINF.INPUT_DATA.NOSTRO_CD;
        IBCSOINT.DATA.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCSOINT.DATA.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBCSOINT.DATA.CUSTNAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCSOINT.DATA.EFFDATE = IBRINRH.KEY.VALUE_DATE;
        IBCSOINT.DATA.PRV_FLAG = IBRINRH.PRV_FLAG;
        IBCSOINT.DATA.RATE_FLAG = IBRINRH.RATE_FLAG;
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'N') {
            IBCSOINT.DATA.RATE_MTH = IBRINRH.RATE_MTH;
            IBCSOINT.DATA.RATE_TYPE = IBRINRH.RATE_TYPE;
            IBCSOINT.DATA.RATE_TERM = IBRINRH.RATE_TERM;
            IBCSOINT.DATA.RATE_PCT = IBRINRH.RATE_PCT;
            IBCSOINT.DATA.RATE_SPREAD = IBRINRH.RATE_SPREAD;
            if (IBCQINF.OUTPUT_DATA.RATE_MTH == '1') {
                IBCSOINT.DATA.RATE = IBRINRH.INT_RATE;
                IBCSOINT.DATA.CON_RATE = IBRINRH.INT_RATE;
            } else {
                IBCSOINT.DATA.CON_RATE = IBRINRH.INT_RATE;
            }
        } else {
            B090_01_GET_L_INFO();
            if (pgmRtn) return;
        }
        if (IBRINRH.CALR_STD == K_360_DAYS) {
            IBCSOINT.DATA.CALR_STD = "01";
        } else if (IBRINRH.CALR_STD == K_365_DAYS) {
            IBCSOINT.DATA.CALR_STD = "02";
        } else if (IBRINRH.CALR_STD == K_366_DAYS) {
            IBCSOINT.DATA.CALR_STD = "03";
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCSOINT;
        SCCFMT.DATA_LEN = 384;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_01_GET_L_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.CCY = IBCQINF.INPUT_DATA.CCY;
        DDCSCINM.INPUT_DATA.AC_NO = IBCQINF.OUTPUT_DATA.CORRAC_NO;
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        IBCSOINT.DATA.RATE_FLAG = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_CINT_FLG;
        IBCSOINT.DATA.PRV_FLAG = IBCSOINT.DATA.RATE_FLAG;
        IBS.init(SCCGWA, DDCSLLCX);
        DDCSLLCX.FUNC = 'L';
        DDCSLLCX.AC = IBCQINF.OUTPUT_DATA.CORRAC_NO;
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
        if (DDCSLLCX.TIR_TYPE == 'N') {
            if (DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 != 0) {
                IBCSOINT.DATA.RATE_MTH = '1';
                IBCSOINT.DATA.RATE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1;
            } else {
                IBCSOINT.DATA.RATE_MTH = '2';
                IBCSOINT.DATA.RATE_TYPE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1;
                IBCSOINT.DATA.RATE_TERM = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1;
                IBCSOINT.DATA.RATE_PCT = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1;
                IBCSOINT.DATA.RATE_SPREAD = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1;
            }
            IBCSOINT.DATA.CON_RATE = DDCSLLCX.CON_RATE;
        }
        IBCSOINT.DATA.OD_RATE = DDCSLLCX.OD_CON_RATE;
        CEP.TRC(SCCGWA, IBCSOINT.DATA.RATE_MTH);
        CEP.TRC(SCCGWA, IBCSOINT.DATA.RATE);
        CEP.TRC(SCCGWA, IBCSOINT.DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCSOINT.DATA.RATE_TERM);
        CEP.TRC(SCCGWA, IBCSOINT.DATA.RATE_SPREAD);
        CEP.TRC(SCCGWA, IBCSOINT.DATA.CON_RATE);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = IBCQINF.INPUT_DATA.CCY;
        S000_CALL_SCSOCCY();
        if (pgmRtn) return;
        IBCSOINT.DATA.CALR_STD = BPCQCCY.DATA.CALR_STD;
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRATIN.RC);
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
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_INRH_LAST() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBTINRH_RD.where = "AC_NO = :IBRINRH.KEY.AC_NO "
            + "AND VALUE_DATE <= :IBRINRH.KEY.VALUE_DATE";
        IBTINRH_RD.fst = true;
        IBTINRH_RD.order = "VALUE_DATE DESC";
        IBS.READ(SCCGWA, IBRINRH, this, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_INRH, IBCRATIN.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINRH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
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
