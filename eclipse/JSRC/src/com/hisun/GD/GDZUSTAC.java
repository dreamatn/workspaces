package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZUSTAC {
    DBParm GDTSTAC_RD;
    String K_OUTPUT_FMT = "DD220";
    String K_LOCAL_CCY = "156";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_FR_AC = " ";
    String WS_TO_AC = " ";
    double WS_AVL_BAL = 0;
    double WS_CURR_BAL = 0;
    double WS_SPAMT = 0;
    char WS_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCEX BPCEX = new BPCEX();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    GDCUSTAC GDCUSTAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCUSTAC GDCUSTAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCUSTAC = GDCUSTAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZUSTAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        B010_CHK_INPUT_DATA();
        B020_UPDATE_STAC_PROC();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUSTAC.AC);
        if (GDCUSTAC.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_UPDATE_STAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCUSTAC.AC;
        GDRSTAC.KEY.AC_SEQ = GDCUSTAC.AC_SEQ;
        T000_READ_UPDATE_PROC();
        GDRSTAC.RELAT_STS = 'R';
        GDRSTAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRSTAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_REC_PROC();
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.upd = true;
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
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
