package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZUSTPL {
    DBParm GDTPLDR_RD;
    String K_GUAOUT = "GUAOUT";
    String WS_ERR_MSG = " ";
    double WS_DR_AMT = 0;
    char WS_TD_OPT = ' ';
    double WS_PAYING_INT = 0;
    String WS_INT_AC = " ";
    double WS_VAL_RAMT = 0;
    double WS_VAL_RAMT1 = 0;
    String WS_AC = " ";
    String WS_SUB_AC = " ";
    char WS_PLDR_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCRMST DDCRMST = new DDCRMST();
    TDCACDRU TDCACDRU = new TDCACDRU();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICQACAC CICQACAC = new CICQACAC();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    double WS_RAMT = 0;
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    TDRCMST TDRCMST = new TDRCMST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    GDCUSTPL GDCUSTPL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCUSTPL GDCUSTPL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCUSTPL = GDCUSTPL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZUSTPL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B020_CHK_PLDR_INF_PROC();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUSTPL.INPUT.AC);
        if (GDCUSTPL.INPUT.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHK_PLDR_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUSTPL.OUTPUT);
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = GDCUSTPL.INPUT.AC;
        T000_READ_GDTPLDR();
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            GDCUSTPL.OUTPUT.RLT_STS = 'Y';
        } else {
            GDCUSTPL.OUTPUT.RLT_STS = 'N';
        }
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
