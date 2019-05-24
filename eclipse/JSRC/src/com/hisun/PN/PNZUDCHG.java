package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUDCHG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm PNTDFT_RD;
    DBParm PNTDFPSW_RD;
    String CPN_U_BPZUBUSE = "BP-U-TLR-BV-USE";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String CPN_U_BPZUCNGM = "BP-U-MAINT-CNGM";
    String CPN_U_BPZQCNGL = "BP-P-INQ-CNGL";
    String K_CNTR_TYPE = "BADR";
    String K_BVCD_QGHP = "C00005";
    String K_BVCD_HDHP = "C00098";
    String K_TBL_FPSW = "PNTDFPSW";
    PNZUDCHG_WS_MSGID WS_MSGID = new PNZUDCHG_WS_MSGID();
    String WS_ERR_MSG = " ";
    char WS_TABLE_FLG = ' ';
    PNZUDCHG_WS_REMARK WS_REMARK = new PNZUDCHG_WS_REMARK();
    int WS_ISS_BR_MSG = 0;
    int WS_ISS_DT = 0;
    char WS_STS = ' ';
    PNZUDCHG_WS_MAC_DA WS_MAC_DA = new PNZUDCHG_WS_MAC_DA();
    short WS_HEAD_NO = 0;
    short WS_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCCBKY SCCCBKY = new SCCCBKY();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCAOTH BPCAOTH = new BPCAOTH();
    PNRDFT PNRDFT = new PNRDFT();
    PNRDFT PNRSAV = new PNRDFT();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUDCHG PNCUDCHG;
    public void MP(SCCGWA SCCGWA, PNCUDCHG PNCUDCHG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUDCHG = PNCUDCHG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNZUDCHG return!");
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
        B203_DFT_MST_PROC();
        B204_WRT_PNTDFPSW_PROC();
        B206_BP_NFHIS();
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFT);
        PNRDFT.KEY.BILL_KND = PNCUDCHG.KND;
        PNRDFT.KEY.BILL_NO = PNCUDCHG.OLD_DFNO;
        CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_KND);
        CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_NO);
        T000_READ_PNTDFT_UPD();
        if (WS_TABLE_FLG == 'N') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND);
        }
        if (PNRDFT.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ISS_BR_MSG = PNRDFT.ISS_BR;
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM);
        }
        if (PNRDFT.ISS_DT != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CHANGE_MUST_ISS_DATE);
        }
        if (PNRDFT.STS != '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_MUST_ISSUE);
        }
        IBS.CLONE(SCCGWA, PNRDFT, PNRSAV);
    }
    public void B202_BV_USE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        if (PNRDFT.KEY.BILL_KND.equalsIgnoreCase("C00005")) {
            BPCFBVQU.TX_DATA.KEY.CODE = K_BVCD_QGHP;
        } else {
            BPCFBVQU.TX_DATA.KEY.CODE = K_BVCD_HDHP;
        }
        S000_CALL_BPZFBVQU();
        WS_HEAD_NO = BPCFBVQU.TX_DATA.HEAD_LENGTH;
        WS_NO = BPCFBVQU.TX_DATA.NO_LENGTH;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        if (PNRDFT.KEY.BILL_KND.equalsIgnoreCase("C00005")) {
            BPCUBUSE.BV_CODE = K_BVCD_QGHP;
        } else {
            BPCUBUSE.BV_CODE = K_BVCD_HDHP;
        }
        CEP.TRC(SCCGWA, WS_HEAD_NO);
        if (WS_HEAD_NO != 0) {
            if (PNCUDCHG.NEW_DFNO == null) PNCUDCHG.NEW_DFNO = "";
            JIBS_tmp_int = PNCUDCHG.NEW_DFNO.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDCHG.NEW_DFNO += " ";
            BPCUBUSE.HEAD_NO = PNCUDCHG.NEW_DFNO.substring(0, WS_HEAD_NO);
        }
        WS_HEAD_NO = (short) (WS_HEAD_NO + 1);
        if (PNCUDCHG.NEW_DFNO == null) PNCUDCHG.NEW_DFNO = "";
        JIBS_tmp_int = PNCUDCHG.NEW_DFNO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDCHG.NEW_DFNO += " ";
        BPCUBUSE.BEG_NO = PNCUDCHG.NEW_DFNO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        if (PNCUDCHG.NEW_DFNO == null) PNCUDCHG.NEW_DFNO = "";
        JIBS_tmp_int = PNCUDCHG.NEW_DFNO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDCHG.NEW_DFNO += " ";
        BPCUBUSE.END_NO = PNCUDCHG.NEW_DFNO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
    }
    public void B203_DFT_MST_PROC() throws IOException,SQLException,Exception {
        PNRDFT.LAST_STS = PNRDFT.STS;
        PNRDFT.STS = '5';
        PNRDFT.USG_RMK = PNCUDCHG.REASON;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_PNTDFT();
        IBS.init(SCCGWA, PNRDFT);
        IBS.CLONE(SCCGWA, PNRSAV, PNRDFT);
        PNRDFT.OLD_DFNO = PNCUDCHG.OLD_DFNO;
        PNRDFT.KEY.BILL_NO = PNCUDCHG.NEW_DFNO;
        PNRDFT.STS = '1';
        PNRDFT.LAST_STS = ' ';
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFT.CHG_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_PNTDFT();
    }
    public void B204_WRT_PNTDFPSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = PNCUDCHG.KND;
        CEP.TRC(SCCGWA, PNCUDCHG.NEW_DFNO);
        PNRDFPSW.KEY.BILL_NO = PNCUDCHG.NEW_DFNO;
        PNRDFPSW.ENCRY_NO = PNCUDCHG.ENCRY_NO;
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
        BPCAOTH.PROD_CD = PNRDFT.PROD_CD;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAOTH;
        S000_CALL_BPZQCNGL();
    }
    public void B302_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = PNCUDCHG.NEW_DFNO;
        BPCUCNGM.KEY.CNTR_TYPE = K_CNTR_TYPE;
        BPCUCNGM.PROD_TYPE = PNRDFT.PROD_CD;
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
        BPCPNHIS.INFO.REF_NO = PNCUDCHG.NEW_DFNO;
        BPCPNHIS.INFO.TX_RMK = PNCUDCHG.REASON;
        BPCPNHIS.INFO.NEW_DAT_PT = PNCUDCHG;
        BPCPNHIS.INFO.FMT_ID = "PNCUDCHG";
        BPCPNHIS.INFO.FMT_ID_LEN = 202;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZUBUSE, BPCUBUSE);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZFBVQU, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZQCNGL, BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_PNTDFT_UPD() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.upd = true;
        IBS.READ(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.REWRITE(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.WRITE(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
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
