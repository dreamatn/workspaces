package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACINT {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMAIN_RD;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBD11   ";
    String WS_ERR_MSG = " ";
    String WS_TABLE_NAME = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINFT IBCQINFT = new IBCQINFT();
    IBCFD11 IBCFD11 = new IBCFD11();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACINT IBCACINT;
    public void MP(SCCGWA SCCGWA, IBCACINT IBCACINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACINT = IBCACINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZACINT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_AC_INFO();
        B030_WRITE_FOMT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACINT.AC_NO);
        CEP.TRC(SCCGWA, IBCACINT.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACINT.CCY);
        if (IBCACINT.AC_NO.trim().length() == 0 
            && (IBCACINT.NOSTR_CD.trim().length() == 0 
            || IBCACINT.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFT);
        if (IBCACINT.AC_NO.trim().length() > 0) {
            IBCQINFT.AC_NO = IBCACINT.AC_NO;
        } else {
            IBCQINFT.NOSTR_CD = IBCACINT.NOSTR_CD;
            IBCQINFT.CCY = IBCACINT.CCY;
        }
        S000_CALL_IBZQINFT();
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINFT.BR 
            && IBCACINT.FUNC == 'I' 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706660500) {
            B020_01_GET_AC_INFO();
        }
        CEP.TRC(SCCGWA, IBCACINT.FUNC);
        if (IBCACINT.FUNC != 'I' 
            && IBCACINT.FUNC != 'B' 
            && IBCQINFT.AC_STS == 'C') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_CLOSE);
        }
    }
    public void B020_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINFT.BR;
        S000_CALL_BPZPQORG();
        if ((BPCPQORG.VIL_TYP.equalsIgnoreCase("51") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706670500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("52") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706671500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("53") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706672500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("54") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706673500)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INQ_NOT_ALLOW);
        }
    }
    public void B030_WRITE_FOMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCFD11);
        IBCFD11.CI_NO = IBCQINFT.CI_NO;
        IBCFD11.NOSTRO_CODE = IBCQINFT.NOSTR_CD;
        IBCFD11.CCY = IBCQINFT.CCY;
        IBCFD11.AC_NO = IBCQINFT.AC_NO;
        IBCFD11.CUSTNME = IBCQINFT.CUSTNME;
        IBCFD11.OPEN_BR = IBCQINFT.OPEN_BR;
        IBCFD11.OPEN_DATE = IBCQINFT.OPEN_DATE;
        if (IBCQINFT.AC_STS == 'C') {
            IBCFD11.CLOSE_DATE = IBCQINFT.CLOS_DATE;
        }
        CEP.TRC(SCCGWA, IBCQINFT.CLOS_DATE);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCFD11;
        SCCFMT.DATA_LEN = 344;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_IBZQINFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFT", IBCQINFT);
        if (IBCQINFT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
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
