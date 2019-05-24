package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFDPAY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATDPAY_RD;
    boolean pgmRtn = false;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARDPAY BARDPAY = new BARDPAY();
    SCCGWA SCCGWA;
    BACFDPAY BACFDPAY;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BACFDPAY BACFDPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACFDPAY = BACFDPAY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZFDPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BACFDPAY.REC_PTR);
        BACFDPAY.RETURN_INFO = 'F';
        IBS.init(SCCGWA, BARDPAY);
        CEP.TRC(SCCGWA, "123456");
        CEP.TRC(SCCGWA, "789");
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BACFDPAY.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARDPAY);
        BACFDPAY.RC.RC_MMO = "BA";
        BACFDPAY.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BARDPAY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACFDPAY.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFDPAY.FUNC == 'L') {
            B100_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFDPAY.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFDPAY.FUNC == 'S') {
            B040_READUPD_BY_IND_PROC();
            if (pgmRtn) return;
        } else if (BACFDPAY.FUNC == 'U') {
            B080_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BACFDPAY.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARDPAY);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B100_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND1_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B040_READUPD_BY_IND_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_IND1_PROC();
        if (pgmRtn) return;
    }
    public void B080_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BATDPAY_RD = new DBParm();
        BATDPAY_RD.TableName = "BATDPAY";
        IBS.READ(SCCGWA, BARDPAY, BATDPAY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_DPAY_NOTFND, BACFDPAY.RC);
            BACFDPAY.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_BY_IND1_PROC() throws IOException,SQLException,Exception {
        BATDPAY_RD = new DBParm();
        BATDPAY_RD.TableName = "BATDPAY";
        BATDPAY_RD.where = "CNTR_NO = :BARDPAY.CNTR_NO "
            + "AND ACCT_CNT = :BARDPAY.ACCT_CNT";
        BATDPAY_RD.fst = true;
        BATDPAY_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BARDPAY, this, BATDPAY_RD);
        CEP.TRC(SCCGWA, "00000000000000");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_DPAY_NOTFND, BACFDPAY.RC);
            BACFDPAY.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARDPAY.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARDPAY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARDPAY.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARDPAY.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BARDPAY.KEY.TX_DT);
        CEP.TRC(SCCGWA, BARDPAY.KEY.CRE_JRN);
        CEP.TRC(SCCGWA, BARDPAY.CNTR_NO);
        CEP.TRC(SCCGWA, BARDPAY.ACCT_CNT);
        CEP.TRC(SCCGWA, BARDPAY.SEQ);
        CEP.TRC(SCCGWA, BARDPAY.ACPT_BR);
        CEP.TRC(SCCGWA, BARDPAY.BILL_NO);
        CEP.TRC(SCCGWA, BARDPAY.DPAY_STS);
        CEP.TRC(SCCGWA, BARDPAY.BILL_STS);
        CEP.TRC(SCCGWA, BARDPAY.AMT_STS);
        CEP.TRC(SCCGWA, BARDPAY.PLDG_STS);
        CEP.TRC(SCCGWA, BARDPAY.BLK_STS);
        CEP.TRC(SCCGWA, BARDPAY.STP_STS);
        CEP.TRC(SCCGWA, BARDPAY.WO_PAY_FLG);
        CEP.TRC(SCCGWA, BARDPAY.CHG_DRW_FLG);
        CEP.TRC(SCCGWA, BARDPAY.ORG_BIL_NO);
        CEP.TRC(SCCGWA, BARDPAY.REMK);
        CEP.TRC(SCCGWA, BARDPAY.BK_STS);
        CEP.TRC(SCCGWA, BARDPAY.BK_X50);
        CEP.TRC(SCCGWA, BARDPAY.CRT_DT);
        CEP.TRC(SCCGWA, BARDPAY.CRT_TLR);
        CEP.TRC(SCCGWA, BARDPAY.UPDT_DT);
        CEP.TRC(SCCGWA, BARDPAY.UPDT_TLR);
        CEP.TRC(SCCGWA, BARDPAY.TS);
        BATDPAY_RD = new DBParm();
        BATDPAY_RD.TableName = "BATDPAY";
        IBS.WRITE(SCCGWA, BARDPAY, BATDPAY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFDPAY.RC);
            BACFDPAY.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BY_DB, BACFDPAY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_UPDATE_IND1_PROC() throws IOException,SQLException,Exception {
        BATDPAY_RD = new DBParm();
        BATDPAY_RD.TableName = "BATDPAY";
        BATDPAY_RD.where = "CNTR_NO = :BARDPAY.CNTR_NO "
            + "AND ACCT_CNT = :BARDPAY.ACCT_CNT "
            + "AND SEQ = :BARDPAY.SEQ";
        BATDPAY_RD.upd = true;
        IBS.READ(SCCGWA, BARDPAY, this, BATDPAY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_DPAY_NOTFND, BACFDPAY.RC);
            BACFDPAY.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARDPAY.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARDPAY.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BATDPAY_RD = new DBParm();
        BATDPAY_RD.TableName = "BATDPAY";
        IBS.REWRITE(SCCGWA, BARDPAY, BATDPAY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BY_DB, BACFDPAY.RC);
            Z_RET();
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
