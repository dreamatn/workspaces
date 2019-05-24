package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSLCH {
    DBParm CITILST_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITACAC_RD;
    String K_OUTPUT_FMT = "CIA42";
    CIZSLCH_WS_MSGID WS_MSGID = new CIZSLCH_WS_MSGID();
    String WS_INFO = " ";
    String WS_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICFA42 CICFA42 = new CICFA42();
    CIRACAC CIRACAC = new CIRACAC();
    CIRILST CIRILST = new CIRILST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSLCH CICSLCH;
    public void MP(SCCGWA SCCGWA, CICSLCH CICSLCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSLCH = CICSLCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSLCH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICFA42);
        IBS.init(SCCGWA, CIRILST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_LIST_CHECK_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSLCH.AGR_NO);
        CEP.TRC(SCCGWA, CICSLCH.LST_TYP);
        CEP.TRC(SCCGWA, CICSLCH.AC_TYP);
        CEP.TRC(SCCGWA, CICSLCH.TR_BRANCH);
        CEP.TRC(SCCGWA, CICSLCH.TL_ID);
        CEP.TRC(SCCGWA, CICSLCH.AC_DATE);
    }
    public void B020_LIST_CHECK_PROC() throws IOException,SQLException,Exception {
        if (!CICSLCH.LST_TYP.equalsIgnoreCase("04")) {
            CIRILST.KEY.AGR_NO = CICSLCH.AGR_NO;
            CICFA42.FMT.AGR_NO = CICSLCH.AGR_NO;
        } else {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = CICSLCH.AGR_NO;
            T000_READ_CITACAC_DEFAULT();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                CIRILST.KEY.AGR_NO = CIRACAC.KEY.ACAC_NO;
                CICFA42.FMT.AGR_NO = CICSLCH.AGR_NO;
            } else {
                CEP.TRC(SCCGWA, "ACAC NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, "不动户记账账号不存在或已�?�?");
            }
        }
        CIRILST.KEY.LST_TYPE = CICSLCH.LST_TYP;
        CICFA42.FMT.LST_TYP = CICSLCH.LST_TYP;
        CIRILST.AC_TYP = CICSLCH.AC_TYP;
        CICFA42.FMT.AC_TYP = CICSLCH.AC_TYP;
        T020_READ_TABLE_CITILST();
        CEP.TRC(SCCGWA, CIRILST.CHK_STS);
        if (CIRILST.CHK_STS == '0') {
            CIRILST.CHK_STS = '1';
            CIRILST.CHK_BR = CICSLCH.TR_BRANCH;
            CIRILST.CHK_TLR = CICSLCH.TL_ID;
            CIRILST.CHK_DT = CICSLCH.AC_DATE;
            T000_REWRITE_TABLE_CITILST();
        } else if (CIRILST.CHK_STS == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LCS_IS_CHK, WS_MSGID);
            WS_INFO = "账户已为核实状�??";
            S000_MSG_PROC();
        } else if (CIRILST.CHK_STS == '2') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LCS_IS_REVOKE, WS_MSGID);
            WS_INFO = "账户现为撤销状�??";
            S000_MSG_PROC();
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LCHK_STS_ERR, WS_MSGID);
            WS_INFO = "状�?��?�错�?";
            S000_MSG_PROC();
        }
        CICFA42.FMT.CHK_STS = CIRILST.CHK_STS;
        CICFA42.FMT.CHK_BR = CIRILST.CHK_BR;
        CICFA42.FMT.CHK_TLR = CIRILST.CHK_TLR;
        CICFA42.FMT.CHK_DT = CIRILST.CHK_DT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFA42;
        SCCFMT.DATA_LEN = 58;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T020_READ_TABLE_CITILST() throws IOException,SQLException,Exception {
        CITILST_RD = new DBParm();
        CITILST_RD.TableName = "CITILST";
        CITILST_RD.eqWhere = "AGR_NO,LST_TYPE,AC_TYP";
        CITILST_RD.errhdl = true;
        CITILST_RD.eqWhere = "DUPKEY";
        CITILST_RD.upd = true;
        IBS.READ(SCCGWA, CIRILST, CITILST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, WS_MSGID);
            WS_INFO = "不存在符合的记录";
            S000_MSG_PROC();
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "MANY ROWS TWO");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITILST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_TABLE_CITILST() throws IOException,SQLException,Exception {
        CITILST_RD = new DBParm();
        CITILST_RD.TableName = "CITILST";
        IBS.REWRITE(SCCGWA, CIRILST, CITILST_RD);
    }
    public void S000_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_INFO);
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1' "
            + "AND ACAC_STS = '0'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
