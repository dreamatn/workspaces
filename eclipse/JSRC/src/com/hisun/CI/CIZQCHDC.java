package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQCHDC {
    DBParm CITONAC_RD;
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRONAC CIRONAC = new CIRONAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQCHDC CICQCHDC;
    public void MP(SCCGWA SCCGWA, CICQCHDC CICQCHDC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQCHDC = CICQCHDC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQCHDC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICQCHDC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICQCHDC.FUNC == 'W') {
            B020_INQUIRE_NEW_CARD();
            if (pgmRtn) return;
        } else if (CICQCHDC.FUNC == 'O') {
            B030_INQUIRE_OLD_CARD();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICQCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQUIRE_NEW_CARD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQCHDC.DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQCHDC.DATA.O_ENTY_TYP);
        if (CICQCHDC.DATA.O_AGR_NO.trim().length() == 0 
            || CICQCHDC.DATA.O_ENTY_TYP == ' ') {
            CEP.TRC(SCCGWA, "OLD AGR-NO AND ENTY-TYP MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRONAC);
        CIRONAC.O_AGR_NO = CICQCHDC.DATA.O_AGR_NO;
        CIRONAC.O_ENTY_TYP = CICQCHDC.DATA.O_ENTY_TYP;
        T000_READ_CITONAC_BY_O();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                CIRONAC.O_AGR_NO = CIRONAC.KEY.N_AGR_NO;
                CIRONAC.O_ENTY_TYP = CIRONAC.KEY.N_ENTY_TYP;
                T000_READ_CITONAC_BY_O();
                if (pgmRtn) return;
            }
        }
        CICQCHDC.DATA.N_AGR_NO = CIRONAC.O_AGR_NO;
        CICQCHDC.DATA.N_ENTY_TYP = CIRONAC.O_ENTY_TYP;
    }
    public void B030_INQUIRE_OLD_CARD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQCHDC.DATA.N_AGR_NO);
        CEP.TRC(SCCGWA, CICQCHDC.DATA.N_ENTY_TYP);
        if (CICQCHDC.DATA.N_AGR_NO.trim().length() == 0 
            || CICQCHDC.DATA.N_ENTY_TYP == ' ') {
            CEP.TRC(SCCGWA, "NEW AGR-NO AND ENTY-TYP MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRONAC);
        CIRONAC.KEY.N_AGR_NO = CICQCHDC.DATA.N_AGR_NO;
        CIRONAC.KEY.N_ENTY_TYP = CICQCHDC.DATA.N_ENTY_TYP;
        T000_READ_CITONAC_BY_N();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "OLD CARD NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_OLD_CARD_INF_NOTFND, CICQCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CICQCHDC.DATA.O_AGR_NO = CIRONAC.O_AGR_NO;
        CICQCHDC.DATA.O_ENTY_TYP = CIRONAC.O_ENTY_TYP;
    }
    public void T000_READ_CITONAC_BY_O() throws IOException,SQLException,Exception {
        CITONAC_RD = new DBParm();
        CITONAC_RD.TableName = "CITONAC";
        CITONAC_RD.eqWhere = "O_AGR_NO , O_ENTY_TYP";
        IBS.READ(SCCGWA, CIRONAC, CITONAC_RD);
    }
    public void T000_READ_CITONAC_BY_N() throws IOException,SQLException,Exception {
        CITONAC_RD = new DBParm();
        CITONAC_RD.TableName = "CITONAC";
        CITONAC_RD.eqWhere = "N_AGR_NO , N_ENTY_TYP";
        IBS.READ(SCCGWA, CIRONAC, CITONAC_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
