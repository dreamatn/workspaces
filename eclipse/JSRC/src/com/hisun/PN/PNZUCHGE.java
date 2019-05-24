package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUCHGE {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm PNTBCC_RD;
    DBParm PNTDFPSW_RD;
    String CPN_U_BPZUBUSE = "BP-U-TLR-BV-USE";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String K_CNTR_TYPE = "CACH";
    String CPN_U_BPZUCNGM = "BP-U-MAINT-CNGM";
    String CPN_U_BPZQCNGL = "BP-P-INQ-CNGL";
    String K_BVCD_BP1 = "C00001";
    String K_TBL_FPSW = "PNTDFPSW";
    String WS_ERR_MSG = " ";
    char WS_TABLE_FLG = ' ';
    PNZUCHGE_WS_MAC_DA WS_MAC_DA = new PNZUCHGE_WS_MAC_DA();
    short WS_HEAD_NO = 0;
    short WS_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCHMPW SCCHMPW = new SCCHMPW();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCAOTH BPCAOTH = new BPCAOTH();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    PNRBCC PNRBCC = new PNRBCC();
    PNRBCC PNRSAV = new PNRBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUCHGE PNCUCHGE;
    public void MP(SCCGWA SCCGWA, PNCUCHGE PNCUCHGE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUCHGE = PNCUCHGE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNZUCHGE return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B201_INPUT_CHK_PROC();
        B202_BV_USE_PROC();
        B301_INQ_GL_MASTER();
        B302_WRITE_GL_MASTER_PROC();
        B204_BCC_MST_PROC();
        B205_WRT_PNTDFPSW_PROC();
        B206_BP_NFHIS();
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRBCC);
        PNRBCC.KEY.BILL_KND = PNCUCHGE.KND;
        PNRBCC.KEY.BILL_NO = PNCUCHGE.OLD_CCNO;
        T000_READ_PNTBCC_UPD();
        if (WS_TABLE_FLG == 'N') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND);
        }
        if (PNRBCC.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM);
        }
        if (PNRBCC.ISS_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CHANGE_MUST_ISS_DATE);
        }
        if (PNRBCC.STS != '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_MUST_ISSUE);
        }
        IBS.CLONE(SCCGWA, PNRBCC, PNRSAV);
    }
    public void B202_BV_USE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = K_BVCD_BP1;
        S000_CALL_BPZFBVQU();
        WS_HEAD_NO = BPCFBVQU.TX_DATA.HEAD_LENGTH;
        WS_NO = BPCFBVQU.TX_DATA.NO_LENGTH;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = K_BVCD_BP1;
        CEP.TRC(SCCGWA, WS_HEAD_NO);
        CEP.TRC(SCCGWA, WS_NO);
        if (WS_HEAD_NO != 0) {
            if (PNCUCHGE.NEW_CCNO == null) PNCUCHGE.NEW_CCNO = "";
            JIBS_tmp_int = PNCUCHGE.NEW_CCNO.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCHGE.NEW_CCNO += " ";
            BPCUBUSE.HEAD_NO = PNCUCHGE.NEW_CCNO.substring(0, WS_HEAD_NO);
        }
        WS_HEAD_NO = (short) (WS_HEAD_NO + 1);
        if (PNCUCHGE.NEW_CCNO == null) PNCUCHGE.NEW_CCNO = "";
        JIBS_tmp_int = PNCUCHGE.NEW_CCNO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCHGE.NEW_CCNO += " ";
        BPCUBUSE.BEG_NO = PNCUCHGE.NEW_CCNO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        if (PNCUCHGE.NEW_CCNO == null) PNCUCHGE.NEW_CCNO = "";
        JIBS_tmp_int = PNCUCHGE.NEW_CCNO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCHGE.NEW_CCNO += " ";
        BPCUBUSE.END_NO = PNCUCHGE.NEW_CCNO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        BPCUBUSE.NUM = 1;
        CEP.TRC(SCCGWA, PNCUCHGE.NEW_CCNO);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        S000_CALL_BPZUBUSE();
    }
    public void B204_BCC_MST_PROC() throws IOException,SQLException,Exception {
        PNRBCC.LAST_STS = PNRBCC.STS;
        PNRBCC.STS = '5';
        PNRBCC.USG_RMK = PNCUCHGE.REASON;
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTBCC();
        IBS.init(SCCGWA, PNRBCC);
        IBS.CLONE(SCCGWA, PNRSAV, PNRBCC);
        PNRBCC.OLD_CCNO = PNRBCC.KEY.BILL_NO;
        PNRBCC.KEY.BILL_NO = PNCUCHGE.NEW_CCNO;
        PNRBCC.LAST_STS = ' ';
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRBCC.CHG_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_PNTBCC();
    }
    public void B205_WRT_PNTDFPSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = "C00001";
        CEP.TRC(SCCGWA, PNCUCHGE.NEW_CCNO);
        PNRDFPSW.KEY.BILL_NO = PNCUCHGE.NEW_CCNO;
        PNRDFPSW.ENCRY_NO = PNCUCHGE.ENCRY_NO;
        PNRDFPSW.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFPSW.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFPSW.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFPSW.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_PNTDFPSW();
    }
    public void B301_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCAOTH);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = K_CNTR_TYPE;
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 10;
        BPCAOTH.PROD_CD = PNRBCC.PRD_CD;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAOTH;
        S000_CALL_BPZQCNGL();
    }
    public void B302_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = PNCUCHGE.NEW_CCNO;
        BPCUCNGM.KEY.CNTR_TYPE = K_CNTR_TYPE;
        BPCUCNGM.PROD_TYPE = PNRBCC.PRD_CD;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
        S000_CALL_BPZUCNGM();
    }
    public void B206_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = " ";
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = PNCUCHGE.NEW_CCNO;
        BPCPNHIS.INFO.TX_RMK = PNCUCHGE.REASON;
        BPCPNHIS.INFO.NEW_DAT_PT = PNCUCHGE;
        BPCPNHIS.INFO.FMT_ID = "PNCUCHGE";
        BPCPNHIS.INFO.FMT_ID_LEN = 202;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZUBUSE, BPCUBUSE);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZFBVQU, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFBVQU.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZQCNGL, BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZUCNGM, BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_PNTBCC_UPD() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        PNTBCC_RD.upd = true;
        IBS.READ(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_PNTBCC() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        IBS.REWRITE(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_PNTBCC() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        IBS.WRITE(SCCGWA, PNRBCC, PNTBCC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_PNTDFPSW() throws IOException,SQLException,Exception {
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        IBS.WRITE(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----WRITE NORMAL----");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "-----WRITE NOT NORMAL----");
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_FPSW_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FPSW;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
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
