package com.hisun.DC;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSWDNT {
    DBParm DCTDCICT_RD;
    String K_PGM_NAME = "DCZSWDNT";
    String CPN_PARM_MT = "DC-RESPONSE-FOR-CARD";
    String K_HIS_REMARK = "GET RESPONSE FOR CARD WRITION";
    String K_HIS_COPYBOOK = "DCCS9480";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT = "DC948";
    String WS_ERR_MSG = " ";
    char WS_WRITE_CARD_STS = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCCF948 DCCF948 = new DCCF948();
    DCRDCICT DCRDCICT = new DCRDCICT();
    SCCGWA SCCGWA;
    DCCS9480 DCCS9480;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCS9480 DCCS9480) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9480 = DCCS9480;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        A010_CHECK_INPUT();
        B000_MAIN_PROC();
        C000_OUTPUT_PROCESS();
        CEP.TRC(SCCGWA, "DCZSWDNT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void A010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCS9480.ORI_TXN_DATE == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DT_M_INPUT);
        }
        if (DCCS9480.ORI_TXN_JRNNO == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_OLD_JRNNO_MUST_INPUT);
        }
        if (DCCS9480.VERIFY_RLT == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_VER_FLG);
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = DCCS9480.ORI_TXN_DATE;
        DCRDCICT.KEY.TXN_JANNO = DCCS9480.ORI_TXN_JRNNO;
        T000_READ_DCTDCICT_UPDATE();
        if (DCCS9480.VERIFY_RLT == '0') {
            DCRDCICT.WRITE_CARD_STS = '3';
            WS_WRITE_CARD_STS = '3';
        } else {
            DCRDCICT.WRITE_CARD_STS = '2';
            WS_WRITE_CARD_STS = '2';
        }
        DCRDCICT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_DCTDCICT();
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF948);
        DCCF948.WRITE_CARD_STS = WS_WRITE_CARD_STS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF948;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTDCICT_UPDATE() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
    }
    public void T000_UPDATE_DCTDCICT() throws IOException,SQLException,Exception {
        null.col = "WRITE_CARD_STS,UPDTBL_DATE,UPDTBL_TLR";
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.where = "TXN_DT = :DCRDCICT.KEY.TXN_DT "
            + "AND TXN_JANNO = :DCRDCICT.KEY.TXN_JANNO";
        IBS.REWRITE(SCCGWA, DCRDCICT, this, DCTDCICT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
