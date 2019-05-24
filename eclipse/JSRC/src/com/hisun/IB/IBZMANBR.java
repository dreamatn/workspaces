package com.hisun.IB;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZMANBR {
    brParm IBTMANIP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    short K_Q_MAX_CNT = 5000;
    String K_IBTMANIP = "IBTMANIP";
    short WS_TS_CNT = 0;
    IBZMANBR_WS_RTN_DATA WS_RTN_DATA = new IBZMANBR_WS_RTN_DATA();
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    int WS_BR = 0;
    char WS_IN_STS = ' ';
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRMANIP IBRMANIP = new IBRMANIP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCMANBR IBCMANBR;
    public void MP(SCCGWA SCCGWA, IBCMANBR IBCMANBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCMANBR = IBCMANBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZMANBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (pgmRtn) return;
        B020_BRW_DETAIL();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANBR.START_DATE);
        if (IBCMANBR.START_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCMANBR.END_DATE);
        if (IBCMANBR.END_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EDATE_MUST_INPUT);
        }
        if (IBCMANBR.START_DATE > IBCMANBR.END_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
        }
        CEP.TRC(SCCGWA, IBCMANBR.BR);
        if (IBCMANBR.BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        } else {
            if (IBCMANBR.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INT_DIFF_BR);
            }
        }
        CEP.TRC(SCCGWA, IBCMANBR.STS);
        if (IBCMANBR.STS == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.STS_MUST_INPUT);
        }
        WS_START_DATE = IBCMANBR.START_DATE;
        WS_END_DATE = IBCMANBR.END_DATE;
        WS_BR = IBCMANBR.BR;
        WS_IN_STS = IBCMANBR.STS;
    }
    public void B020_BRW_DETAIL() throws IOException,SQLException,Exception {
        S000_MPAG_INIT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBRMANIP);
        IBRMANIP.IPT_BR = IBCMANBR.BR;
        IBRMANIP.STS = IBCMANBR.STS;
        T000_STARTBR_IBTMANIP();
        if (pgmRtn) return;
        T000_READNEXT_IBTMANIP();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        while (WS_TABLE_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_RTN_DATA.WS_AC_DATE = IBRMANIP.KEY.AC_DATE;
            WS_RTN_DATA.WS_JRN_NO = IBRMANIP.KEY.JRN_NO;
            WS_RTN_DATA.WS_AC_DATE = IBRMANIP.KEY.AC_DATE;
            WS_RTN_DATA.WS_JRN_NO = IBRMANIP.KEY.JRN_NO;
            WS_RTN_DATA.WS_IPT_TLR = IBRMANIP.IPT_TLR;
            WS_RTN_DATA.WS_AC_NO = IBRMANIP.AC_NO;
            WS_RTN_DATA.WS_CCY = IBRMANIP.CCY;
            WS_RTN_DATA.WS_SIGN = IBRMANIP.SIGN;
            WS_RTN_DATA.WS_OTH_AC_TYP = IBRMANIP.OTH_AC_TYP;
            WS_RTN_DATA.WS_OTH_AC_NO = IBRMANIP.OTH_AC_NO;
            WS_RTN_DATA.WS_AMT = IBRMANIP.AMT;
            WS_RTN_DATA.WS_STS = IBRMANIP.STS;
            WS_RTN_DATA.WS_CHK_TLR = IBRMANIP.CHK_TLR;
            WS_RTN_DATA.WS_AUTH_TLR = IBRMANIP.AUTH_TLR;
            S000_WRITE_QUEUE();
            if (pgmRtn) return;
            T000_READNEXT_IBTMANIP();
            if (pgmRtn) return;
        }
    }
    public void S000_MPAG_INIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 131;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        if (WS_TS_CNT > K_Q_MAX_CNT) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OUTOF_TS);
        }
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, "MPAG IN");
        SCCMPAG.FUNC = 'D';
        SCCMPAG.MAX_COL_NO = 131;
        SCCMPAG.DATA_LEN = 131;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA);
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = (short) (WS_TS_CNT + 1);
        CEP.TRC(SCCGWA, WS_TS_CNT);
    }
    public void T000_STARTBR_IBTMANIP() throws IOException,SQLException,Exception {
        IBTMANIP_BR.rp = new DBParm();
        IBTMANIP_BR.rp.TableName = "IBTMANIP";
        IBTMANIP_BR.rp.where = "AC_DATE >= :WS_START_DATE "
            + "AND AC_DATE <= :WS_END_DATE "
            + "AND IPT_BR = :WS_BR "
            + "AND STS = :WS_IN_STS";
        IBTMANIP_BR.rp.order = "AC_DATE, JRN_NO";
        IBS.STARTBR(SCCGWA, IBRMANIP, this, IBTMANIP_BR);
    }
    public void T000_READNEXT_IBTMANIP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRMANIP, this, IBTMANIP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMANIP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
