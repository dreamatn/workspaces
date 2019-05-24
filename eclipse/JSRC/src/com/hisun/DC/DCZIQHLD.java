package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIQHLD {
    DBParm DDTHLD_RD;
    brParm DCTIAACR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_IAACR_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DDRHLD DDRHLD = new DDRHLD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIQHLD DCCIQHLD;
    public void MP(SCCGWA SCCGWA, DCCIQHLD DCCIQHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIQHLD = DCCIQHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIQHLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIQHLD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_INQ_AC_HLD_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCIQHLD.INP_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO, DCCIQHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '1';
        DCCPACTY.INPUT.AC = DCCIQHLD.INP_DATA.AC;
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        if (DCCPACTY.OUTPUT.AC_TYPE != 'K' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'D' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'V' 
            && DCCPACTY.OUTPUT.AC_TYPE != 'I') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_TYPE_INVALID, DCCIQHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((DCCPACTY.OUTPUT.AC_TYPE == 'D') 
            && (DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("IB") 
            || DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("LN"))) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_TYPE_INVALID, DCCIQHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_INQ_AC_HLD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCIQHLD.INP_DATA.AC;
        DDRHLD.HLD_TYP = '1';
        DDRHLD.SPR_BR_TYP = '1';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_1();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCIQHLD.INP_DATA.AC;
        DDRHLD.HLD_TYP = '1';
        DDRHLD.SPR_BR_TYP = '2';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_2();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCIQHLD.INP_DATA.AC;
        DDRHLD.HLD_TYP = '2';
        DDRHLD.SPR_BR_TYP = '1';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_3();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCIQHLD.INP_DATA.AC;
        DDRHLD.SPR_BR_TYP = '2';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_4();
        if (pgmRtn) return;
    }
    public void B040_INQ_CARD_PSK_STD_HLD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRHLD.HLD_TYP = '1';
        DDRHLD.SPR_BR_TYP = '1';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_1();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRHLD.HLD_TYP = '1';
        DDRHLD.SPR_BR_TYP = '2';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_2();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRHLD.HLD_TYP = '2';
        DDRHLD.SPR_BR_TYP = '1';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_3();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRHLD.SPR_BR_TYP = '2';
        DDRHLD.HLD_STS = 'N';
        T000_READ_DDTHLD_4();
        if (pgmRtn) return;
    }
    public void B050_INQ_MST_AC_HLD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.SUB_AC = DCCPACTY.OUTPUT.STD_AC;
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        while (WS_IAACR_FLG != 'N') {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.AC = DCRIAACR.KEY.VIA_AC;
            DDRHLD.HLD_TYP = '1';
            DDRHLD.SPR_BR_TYP = '1';
            DDRHLD.HLD_STS = 'N';
            T000_READ_DDTHLD_1();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.AC = DCRIAACR.KEY.VIA_AC;
            DDRHLD.HLD_TYP = '1';
            DDRHLD.SPR_BR_TYP = '2';
            DDRHLD.HLD_STS = 'N';
            T000_READ_DDTHLD_2();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.AC = DCRIAACR.KEY.VIA_AC;
            DDRHLD.HLD_TYP = '2';
            DDRHLD.SPR_BR_TYP = '1';
            DDRHLD.HLD_STS = 'N';
            T000_READ_DDTHLD_3();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.AC = DCRIAACR.KEY.VIA_AC;
            DDRHLD.SPR_BR_TYP = '2';
            DDRHLD.HLD_STS = 'N';
            T000_READ_DDTHLD_4();
            if (pgmRtn) return;
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTHLD_1() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_TYP = :DDRHLD.HLD_TYP "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND HLD_STS = :DDRHLD.HLD_STS";
        DDTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCCIQHLD.OUT_DATA.LAW_AC = 'Y';
        }
    }
    public void T000_READ_DDTHLD_2() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_TYP = :DDRHLD.HLD_TYP "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND HLD_STS = :DDRHLD.HLD_STS";
        DDTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCCIQHLD.OUT_DATA.BNK_AC = 'Y';
        }
    }
    public void T000_READ_DDTHLD_3() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_TYP = :DDRHLD.HLD_TYP "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND HLD_AMT > 0";
        DDTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCCIQHLD.OUT_DATA.LAW_AMT = 'Y';
        }
    }
    public void T000_READ_DDTHLD_4() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_TYP < > '1' "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND HLD_AMT > 0";
        DDTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCCIQHLD.OUT_DATA.BNK_AMT = 'Y';
        }
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "SUB_AC = :DCRIAACR.SUB_AC "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_BR.rp.order = "VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_FLG = 'Y';
        } else {
            WS_IAACR_FLG = 'N';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_IAACR_FLG);
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIQHLD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCIQHLD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIQHLD=");
            CEP.TRC(SCCGWA, DCCIQHLD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
