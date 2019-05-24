package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPRSDL {
    DBParm TDTIREV_RD;
    brParm TDTIREV_BR = new brParm();
    DBParm TDTOTHE_RD;
    DBParm TDTLMT_RD;
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD545";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String WS_MSGID = " ";
    char WS_TABLE_FLG = ' ';
    char WS_TABLE_FLG1 = ' ';
    int WS_CNT_IREV = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDROTHE TDROTHE = new TDROTHE();
    TDRIREV TDRIREV = new TDRIREV();
    TDRLMT TDRLMT = new TDRLMT();
    TDCORSDL TDCORSDL = new TDCORSDL();
    SCCGWA SCCGWA;
    TDCPRSDL TDCPRSDL;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCPRSDL TDCPRSDL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPRSDL = TDCPRSDL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPRSDL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRSDL);
        B110_READ_TDTOTHE();
        B111_READ_TDTLMT();
        B300_OUTPUT_INF();
        B210_REWRT_TDTOTHE();
        B210_STARTBR_TDTIREV();
        T000_READNEXT_TDTIREV();
        while (WS_TABLE_FLG1 != 'N') {
            TDRIREV.CON_RATE = TDCPRSDL.ZZ_RAT;
            if (TDRIREV.INT_SEL != '4') {
                TDRIREV.INT_SEL = '4';
            }
            T000_REWRITE_TDTIREV();
            T000_READNEXT_TDTIREV();
        }
        T000_ENDBR_TDTIREV();
    }
    public void T000_ENDBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTIREV_BR);
    }
    public void T000_REWRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.REWRITE(SCCGWA, TDRIREV, TDTIREV_RD);
    }
    public void B210_STARTBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.ACTI_NO = TDCPRSDL.ACTI_NO;
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRIREV.ACTI_NO);
        T000_STARTBR_TDTIREV();
    }
    public void B110_READ_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCPRSDL.PROD_CD;
        TDROTHE.KEY.ACTI_NO = TDCPRSDL.ACTI_NO;
        CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        T000_READ_TDTOTHE();
        if (WS_TABLE_FLG == 'N') {
            CEP.TRC(SCCGWA, "1111111");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
        if (TDROTHE.DDT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DDT_LT_ACDT);
        }
    }
    public void B111_READ_TDTLMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.ACTI_NO = TDCPRSDL.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCPRSDL.PROD_CD;
        TDRLMT.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRLMT.KEY.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        CEP.TRC(SCCGWA, TDRLMT.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDRLMT.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRLMT.KEY.BR);
        CEP.TRC(SCCGWA, TDRLMT.KEY.CHNL_NO);
        T000_READ_TDTLMT();
        if (WS_TABLE_FLG == 'N') {
            CEP.TRC(SCCGWA, "---TDTLMT NOT FOUND-----");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
    }
    public void B210_REWRT_TDTOTHE() throws IOException,SQLException,Exception {
        if (TDCPRSDL.SDT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SDT_LT_ACDT);
        }
        CEP.TRC(SCCGWA, TDROTHE.DDT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (TDROTHE.DDT <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SDT_GT_DDT);
        }
        if (TDCPRSDL.SDT == 0) {
        } else {
            CEP.TRC(SCCGWA, TDCPRSDL.SDT);
            CEP.TRC(SCCGWA, TDCPRSDL.ZZ_RAT);
            TDROTHE.CONT_RAT = TDCPRSDL.ZZ_RAT;
            TDROTHE.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDROTHE.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTOTHE();
        }
        if (TDCPRSDL.MAX_RAT < TDCPRSDL.ZZ_RAT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_RATE_ERROR);
        }
    }
    public void B310_CHECK_INF() throws IOException,SQLException,Exception {
        if (TDROTHE.ACTI_FLG == '1') {
            CEP.TRC(SCCGWA, "--------1---------");
            if (TDROTHE.SDT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_NOT_QIXI);
            }
            if (TDROTHE.DDT <= SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, "--------2---------");
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_DAOQI);
            }
        }
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRSDL.PROD_CD);
        CEP.TRC(SCCGWA, TDCPRSDL.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.MIN_RAT);
        CEP.TRC(SCCGWA, TDROTHE.MAX_RAT);
        CEP.TRC(SCCGWA, TDROTHE.CONT_RAT);
        CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
        CEP.TRC(SCCGWA, TDROTHE.END_DATE);
        CEP.TRC(SCCGWA, TDRLMT.AGN_USE_BAL);
        CEP.TRC(SCCGWA, TDROTHE.SDT);
        CEP.TRC(SCCGWA, TDROTHE.DDT);
        IBS.init(SCCGWA, TDCORSDL);
        TDCORSDL.PROD_CD = TDCPRSDL.PROD_CD;
        TDCORSDL.ACTI_NO = TDCPRSDL.ACTI_NO;
        TDCORSDL.MIN_RAT = TDROTHE.MIN_RAT;
        TDCORSDL.MAX_RAT = TDROTHE.MAX_RAT;
        TDCORSDL.ZZ_RAT = TDROTHE.CONT_RAT;
        TDCORSDL.SHX_DT = TDROTHE.STR_DATE;
        TDCORSDL.SHI_DT = TDROTHE.END_DATE;
        TDCORSDL.AGN_USE_BAL = TDRLMT.AGN_USE_BAL;
        TDCORSDL.SDT = TDROTHE.SDT;
        TDCORSDL.DDT = TDROTHE.DDT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRD_FMT;
        SCCFMT.DATA_PTR = TDCORSDL;
        SCCFMT.DATA_LEN = 120;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READNEXT_TDTIREV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRIREV, this, TDTIREV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "WS-TDTIREV-FOUND");
            WS_TABLE_FLG1 = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "WS-TDTIREV-NOT-FOUND");
            WS_TABLE_FLG1 = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTIREV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
        WS_CNT_IREV += 1;
        if (WS_TABLE_FLG1 == 'N' 
            && WS_CNT_IREV == 1) {
            CEP.TRC(SCCGWA, "222222");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
    }
    public void T000_STARTBR_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.where = "ACTI_NO = :TDRIREV.ACTI_NO";
        TDTIREV_BR.rp.upd = true;
        TDTIREV_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND ACTI_NO = :TDROTHE.KEY.ACTI_NO";
        TDTOTHE_RD.upd = true;
        IBS.READ(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "WS-TDTOTHE-FOUND");
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "WS-TDTOTHE-NOT-FOUND");
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.where = "ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND PROD_CD = :TDRLMT.KEY.PROD_CD "
            + "AND BR = '043999' "
            + "AND LM_CNT = '1'";
        IBS.READ(SCCGWA, TDRLMT, this, TDTLMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTLMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.REWRITE(SCCGWA, TDROTHE, TDTOTHE_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
