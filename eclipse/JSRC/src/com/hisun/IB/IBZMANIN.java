package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZMANIN {
    DBParm IBTMANIP_RD;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBA62";
    String K_IBTMANIP = "IBTMANIP";
    char WS_OTH_AC_ATTR = ' ';
    int WS_OTH_NAME = 0;
    int WS_OTH_BR = 0;
    String WS_OTH_CORP = " ";
    String WS_CORP = " ";
    short WS_I = 0;
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCOMNIN IBCOMNIN = new IBCOMNIN();
    IBRMST IBRMST = new IBRMST();
    IBRMANIP IBRMANIP = new IBRMANIP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCMANIN IBCMANIN;
    public void MP(SCCGWA SCCGWA, IBCMANIN IBCMANIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCMANIN = IBCMANIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZMANIN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B020_INQ_DETAIL();
        B030_PROC_OUTPUT();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANIN.AC_DATE);
        if (IBCMANIN.AC_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_DATE_M);
        }
        CEP.TRC(SCCGWA, IBCMANIN.JRN_NO);
        if (IBCMANIN.JRN_NO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.JRNNO_M);
        }
    }
    public void B020_INQ_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMANIP);
        IBRMANIP.KEY.AC_DATE = IBCMANIN.AC_DATE;
        IBRMANIP.KEY.JRN_NO = IBCMANIN.JRN_NO;
        T000_READ_IBTMANIP();
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_NOTFND);
        }
        if (IBRMANIP.STS != 'P') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.STS);
        }
    }
    public void B030_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOMNIN);
        IBCOMNIN.AC_DATE = IBRMANIP.KEY.AC_DATE;
        IBCOMNIN.JRN_NO = IBRMANIP.KEY.JRN_NO;
        IBCOMNIN.STS = IBRMANIP.STS;
        IBCOMNIN.IPT_TLR = IBRMANIP.IPT_TLR;
        IBCOMNIN.CHK_TLR = IBRMANIP.CHK_TLR;
        IBCOMNIN.AUTH_TLR = IBRMANIP.AUTH_TLR;
        IBCOMNIN.AC_NO = IBRMANIP.AC_NO;
        IBCOMNIN.NOS_CD = IBRMANIP.NOSTRO_CODE;
        IBCOMNIN.CCY = IBRMANIP.CCY;
        IBCOMNIN.SIGN = IBRMANIP.SIGN;
        IBCOMNIN.AMT = IBRMANIP.AMT;
        IBCOMNIN.OTH_AC_TYP = IBRMANIP.OTH_AC_TYP;
        IBCOMNIN.OTH_AC_NO = IBRMANIP.OTH_AC_NO;
        IBCOMNIN.POST_CTR = IBRMANIP.POST_CTR;
        IBCOMNIN.SUSPD_NO = IBRMANIP.SUSPD_NO;
        IBCOMNIN.VAL_DATE = IBRMANIP.VAL_DATE;
        IBCOMNIN.REF_NO = IBRMANIP.REF_NO;
        IBCOMNIN.OTH_REF_NO = IBRMANIP.OTH_REF_NO;
        IBCOMNIN.RMK = IBRMANIP.RMK;
        IBCOMNIN.OTH_RMK = IBRMANIP.OTH_RMK;
        IBCOMNIN.MMO = IBRMANIP.MMO;
        IBCOMNIN.OTH_MMO = IBRMANIP.OTH_MMO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOMNIN;
        SCCFMT.DATA_LEN = 978;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_IBTMANIP() throws IOException,SQLException,Exception {
        IBTMANIP_RD = new DBParm();
        IBTMANIP_RD.TableName = "IBTMANIP";
        IBS.READ(SCCGWA, IBRMANIP, IBTMANIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMANIP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
