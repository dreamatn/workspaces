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
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPRSD {
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTOTHE_RD;
    DBParm TDTLMT_RD;
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD544";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String WS_MSGID = " ";
    char WS_TABLE_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDROTHE TDROTHE = new TDROTHE();
    TDRLMT TDRLMT = new TDRLMT();
    TDCORSD TDCORSD = new TDCORSD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    TDCPRSD TDCPRSD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCPRSD TDCPRSD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPRSD = TDCPRSD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPRSD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRSD);
        B110_READ_TDTOTHE();
        B230_WRI_NFIN_HIS_PROC();
        B300_OUTPUT_INF();
        B310_CHECK_INF();
        B210_REWRT_TDTOTHE();
    }
    public void B110_READ_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCPRSD.PROD_CD;
        TDROTHE.KEY.ACTI_NO = TDCPRSD.ACTI_NO;
        T000_READ_TDTOTHE();
        if (WS_TABLE_FLG == 'N') {
            CEP.TRC(SCCGWA, "1111111");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
        CEP.TRC(SCCGWA, TDCPRSD.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCPRSD.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037") 
            && (TDROTHE.SDT == 0 
            && TDROTHE.DDT == 0)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ZTS_SDT_NULL_ERR);
        }
    }
    public void B111_READ_TDTLMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.ACTI_NO = TDCPRSD.ACTI_NO;
        TDRLMT.KEY.PROD_CD = TDCPRSD.PROD_CD;
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
        if (TDCPRSD.SHX_DT == 0) {
        } else {
            CEP.TRC(SCCGWA, TDCPRSD.FUNC);
            TDROTHE.SET_FLG = TDCPRSD.FUNC;
            TDROTHE.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDROTHE.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTOTHE();
        }
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCORSD);
        TDCORSD.PROD_CD = TDCPRSD.PROD_CD;
        TDCORSD.ACTI_NO = TDCPRSD.ACTI_NO;
        CEP.TRC(SCCGWA, TDROTHE.SET_FLG);
        TDCORSD.FUNC = TDROTHE.SET_FLG;
        TDCORSD.MIN_RAT = TDROTHE.MIN_RAT;
        TDCORSD.MAX_RAT = TDROTHE.MAX_RAT;
        TDCORSD.ZZ_RAT = TDROTHE.CONT_RAT;
        TDCORSD.SHX_DT = TDROTHE.STR_DATE;
        TDCORSD.SHI_DT = TDROTHE.END_DATE;
        TDCORSD.AGN_USE_BAL = TDRLMT.AGN_USE_BAL;
        TDCORSD.SDT = TDROTHE.SDT;
        TDCORSD.DDT = TDROTHE.DDT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRD_FMT;
        SCCFMT.DATA_PTR = TDCORSD;
        SCCFMT.DATA_LEN = 121;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B310_CHECK_INF() throws IOException,SQLException,Exception {
        if (TDROTHE.ACTI_FLG == '1') {
            if (SCCGWA.COMM_AREA.AC_DATE >= TDROTHE.SDT) {
                CEP.TRC(SCCGWA, "--------2---------");
                CEP.TRC(SCCGWA, TDROTHE.END_DATE);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_QIXI);
            }
            if (TDROTHE.STR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, "--------3---------");
                CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOT_SET_ST);
            }
        }
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P630";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = "TDCORSD";
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
        IBS.READ(SCCGWA, TDRLMT, TDTLMT_RD);
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
